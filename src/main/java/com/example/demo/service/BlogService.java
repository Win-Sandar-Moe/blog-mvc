package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.PostDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
	private final CategoryDao categoryDao;
	private final UserDao userDao;
	private final PostDao postDao;
	public List<Category> findAllCategory(){
		return categoryDao.findAll();
	}
	
	public void saveCategory(Category category) {
		categoryDao.save(category);
	}
	public List<User> findAllUser(){
		return userDao.findAll();
	}
	public void saveUser(User user) {
		userDao.save(user);
	}
	
	public void savePost(Post post) {
		Category category=categoryDao.findById(post.getCategory().getId())
				.get();
		User user=userDao.findById(post.getUser().getId()).get();
		category.addPost(post);
		user.addPost(post);
		postDao.save(post);
	}
	public List<Post> findAllPost(){
		return postDao.findAll();
	}
}





