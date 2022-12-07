package net.abhi.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.abhi.springboot.dto.CommentDto;
import net.abhi.springboot.dto.PostDto;
import net.abhi.springboot.service.CommentService;
import net.abhi.springboot.service.PostService;
@Controller
public class CommentController {
   
	private CommentService commentService;
	private PostService postService;
	
    @Autowired
	public CommentController(CommentService commentService, PostService postService) {
		this.commentService = commentService;
		this.postService = postService;
	}
	
	@PostMapping("/{postUrl}/comments")
	public String createComment(@PathVariable("postUrl") String postUrl,
			                     @Valid @ModelAttribute("comment") CommentDto commentDto, BindingResult result, Model model) {
		
		PostDto postDto = postService.findPostByUrl(postUrl);
		if(result.hasErrors())
		{
			model.addAttribute("post", postDto);
			model.addAttribute("comment", commentDto);
			return "blog/blog_posts";
		}
		
		commentService.createComment(postUrl, commentDto);
		return "redirect:/post/" + postUrl;
		
	}
	
}
