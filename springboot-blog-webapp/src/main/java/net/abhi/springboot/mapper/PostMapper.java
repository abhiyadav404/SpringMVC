package net.abhi.springboot.mapper;

import java.util.stream.Collectors;

import net.abhi.springboot.dto.PostDto;
import net.abhi.springboot.entity.Post;

public class PostMapper {
	//map Post entity to PostDto
	public static PostDto mapToPostDto(Post post) {
		
		return PostDto.builder()
				      .id(post.getId())
				      .title(post.getTitle())
				      .url(post.getUrl())
				      .content(post.getContent())
				      .shortDescription(post.getShortDescription())
				      .createdOn(post.getCreatedOn())
				      .upatedOn(post.getUpatedOn())
				      .comments(post.getComments().stream()
				    		  .map((comment)->CommentMapper.mapToCommentDto(comment))
				    		  .collect(Collectors.toSet()))
				      .build();
	}
	
	//map PostDto to Post entity
	public static Post mapPostDtoToPost(PostDto postDto) {
		
		return Post.builder()
				   .id(postDto.getId())
				   .title(postDto.getTitle())
				   .url(postDto.getUrl())
				   .content(postDto.getContent())
				   .shortDescription(postDto.getShortDescription())
				   .createdOn(postDto.getCreatedOn())
				   .upatedOn(postDto.getUpatedOn())
				   .build();
	}

}
