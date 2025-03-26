package com.admin.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
	private LocalDate timestamp;
	  private String message;
	  private String details;
	  private String status;
}