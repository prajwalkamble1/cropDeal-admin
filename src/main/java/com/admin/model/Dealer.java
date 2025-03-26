package com.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dealer {
	private int farmerId;
	private String name;
	private String username;
	private String password;
	int rating;
}
