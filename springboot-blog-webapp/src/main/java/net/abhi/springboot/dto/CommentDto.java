package net.abhi.springboot.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty(message = "Email shouldn't be empty")
	@Email
	private String email;
	@NotEmpty(message = "Message body shouldn't be empty")
	private String content;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	

	
}
