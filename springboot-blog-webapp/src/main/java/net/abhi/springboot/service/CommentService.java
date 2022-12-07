package net.abhi.springboot.service;

import java.util.List;

import net.abhi.springboot.dto.CommentDto;

public interface CommentService {
   void createComment(String postUrl, CommentDto commentDto);

    List<CommentDto> findAllComments();

	void deleteComment(Long commentId);
	
	List<CommentDto> findCommentsByPost();
}
