package net.abhi.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.abhi.springboot.dto.CommentDto;
import net.abhi.springboot.entity.Comment;
import net.abhi.springboot.entity.Post;
import net.abhi.springboot.entity.User;
import net.abhi.springboot.mapper.CommentMapper;
import net.abhi.springboot.repository.CommentRepository;
import net.abhi.springboot.repository.PostRepository;
import net.abhi.springboot.repository.UserRepository;
import net.abhi.springboot.util.SecurityUtils;
@Service
public class CommentServiceImpl implements CommentService{
    
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private UserRepository userRepository;
	@Autowired

	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,
			UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void createComment(String postUrl, CommentDto commentDto) {
		Post post = postRepository.findByUrl(postUrl).get();
		Comment comment = CommentMapper.mapToComment(commentDto);
		comment.setPost(post);
		commentRepository.save(comment);
		
	}

	@Override
	public List<CommentDto> findAllComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream()
				       .map(CommentMapper::mapToCommentDto)
				       .collect(Collectors.toList());
	}

	@Override
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
		
	}

	@Override
	public List<CommentDto> findCommentsByPost() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Comment> comments=commentRepository.findCommentsByPost(userId);
		return comments.stream().map((comment)->CommentMapper.mapToCommentDto(comment))
				                .collect(Collectors.toList());
	}

}
