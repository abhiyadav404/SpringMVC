package net.abhi.springboot.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	
	private Long id;
	@NotEmpty(message = "Post title shouldn't be Empty.")
	private String title;
	private String url;
	@NotEmpty(message = "Post content shouldn't be Empty.")
	private String content;
	@NotEmpty(message = "Post short description shouldn't be Empty.")
	private String shortDescription;
	private LocalDateTime createdOn;
	private LocalDateTime upatedOn;
	private Set<CommentDto> comments;
}
