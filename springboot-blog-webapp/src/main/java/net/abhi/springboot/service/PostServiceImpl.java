package net.abhi.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.abhi.springboot.dto.PostDto;
import net.abhi.springboot.entity.Post;
import net.abhi.springboot.entity.User;
import net.abhi.springboot.mapper.PostMapper;
import net.abhi.springboot.repository.PostRepository;
import net.abhi.springboot.repository.UserRepository;
import net.abhi.springboot.util.SecurityUtils;
@Service
public class PostServiceImpl implements PostService{
    
	private PostRepository postRepository;
	private UserRepository userRepository;
	@Autowired
	public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<PostDto> findAllPosts() {
		
		List<Post> posts= postRepository.findAll();
		return posts.stream().map(PostMapper::mapToPostDto)
				             .collect(Collectors.toList());
	}

	@Override
	public void createPost(PostDto postDto) {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepository.findByEmail(email);
		Post post=PostMapper.mapPostDtoToPost(postDto);
		post.setCreatedBy(user);
		postRepository.save(post);
	}

	@Override
	public PostDto findPostById(Long postId) {
		Post post = postRepository.findById(postId).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public void updatePost(PostDto postDto) {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Post post = PostMapper.mapPostDtoToPost(postDto);
		post.setCreatedBy(createdBy);
		postRepository.save(post);
		
	}

	@Override
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
		
	}

	@Override
	public PostDto findPostByUrl(String postUrl) {
		Post post = postRepository.findByUrl(postUrl).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public List<PostDto> searchPosts(String query) {
		List<Post> posts= postRepository.searchPosts(query);
		return posts.stream()
				    .map(PostMapper::mapToPostDto)
				    .collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByUser() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		
		return postRepository.findPostsByUser(userId).stream()
				                                     .map(PostMapper::mapToPostDto)
				                                     .collect(Collectors.toList());
	}

}
