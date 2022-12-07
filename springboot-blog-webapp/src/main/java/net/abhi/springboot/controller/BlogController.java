package net.abhi.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import net.abhi.springboot.dto.CommentDto;
import net.abhi.springboot.dto.PostDto;
import net.abhi.springboot.service.PostService;

@Controller
public class BlogController {
   
	private PostService postService;
    @Autowired
	public BlogController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/")
	public String viewBlogPosts(Model model) {
		List<PostDto> postsResponse = postService.findAllPosts();
		model.addAttribute("postsResponse", postsResponse);
		return "blog/view_posts";
	}
	
	//handler method to handle the view post request
	@GetMapping("/post/{postUrl}")
	public String showPost(@PathVariable("postUrl") String postUrl, Model model) {
		PostDto post = postService.findPostByUrl(postUrl);
		CommentDto commentDto=new CommentDto();
		model.addAttribute("post", post);
		model.addAttribute("comment", commentDto);
		return "blog/blog_posts";
	}
	
	//handler method to handle blog post search request
	//  http://localhost:8080/page/search?query=java
	
	@GetMapping("/page/search")
	public String searchPosts(@RequestParam(value = "query") String query,Model model) {
		List<PostDto> postsResponse = postService.searchPosts(query);
		model.addAttribute("postsResponse", postsResponse);
		return "blog/view_posts";
	}
}
