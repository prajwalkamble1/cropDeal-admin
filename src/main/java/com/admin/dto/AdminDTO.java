package com.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
	
	@NotBlank(message = "Name can not be empty")
	@Size(min=3, max=25, message="First name must be between 3 and 25 characters")
	@Pattern(regexp="^[A-Za-z]+$")
	private String name;
	
	@NotBlank(message = "Username can not be empty")
	private String userName;
	
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@NotBlank(message = "Password is mandatory")
	@Size(min=8, message="Password must be atleast 8 characters long")
	private String password;
}
