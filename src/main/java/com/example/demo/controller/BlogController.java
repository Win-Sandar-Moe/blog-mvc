package com.example.demo.controller;

import com.example.demo.dao.CategoryDao;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.BlogService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BlogController {
	private final CategoryDao categoryDao;
	private final BlogService blogService;

	

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("users", blogService.findAllUser());
		model.addAttribute("categories",blogService.findAllCategory());
		model.addAttribute("posts", blogService.findAllPost());
		model.addAttribute("user", new User());
		model.addAttribute("category", new Category());
		model.addAttribute("post", new Post());
		return "home";
	}
	@PostMapping("/add-post")
	public String savePost(Post post,BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/";
		}
		blogService.savePost(post);
		return "redirect:/";
	}
	@GetMapping("/list-post")
	public String listPost(Model model) {
		model.addAttribute("posts",blogService.findAllPost());
		return "post/posts";
	}
	

	@GetMapping("/list-category")
	public String listCategory(Model model) {
		model.addAttribute("categories", blogService.findAllCategory());
		return "category/categories";
	}
	
	@PostMapping("/add-categoy")
	public String saveCategory(Category category, BindingResult rs) {
		if (rs.hasErrors()) {
			return "redirect:/";
		}
		blogService.saveCategory(category);
		return "redirect:/";
	}
	
	@PostMapping("/add-user")
	public String saveUser(User user,BindingResult rs) {
		if(rs.hasErrors()) {
			return "redirect:/";
		}
		blogService.saveUser(user);
		return "redirect:/";
	}
	@GetMapping("/list-user")
	public String listUser(Model model){
		model.addAttribute("users", blogService.findAllUser());
		return "user/users";
	}
	@ModelAttribute("postCount")
	public int postCount() {
		return blogService.findAllPost().size();
	}
	@ModelAttribute("categoryCount")
	public int categoryCount() {
		return blogService.findAllCategory().size();
	}
	@ModelAttribute("authorCount")
	public int authorCount() {
		return blogService.findAllUser().size();
	}
	

}










