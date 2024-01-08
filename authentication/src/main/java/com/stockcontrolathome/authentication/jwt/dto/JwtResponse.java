package com.stockcontrolathome.authentication.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private String token;

	private String bearer = "Bearer";

	public JwtResponse(String token) {
		this.token = token;
	}
}
