package com.interview.tenpo.dto;

import lombok.*;

import java.io.Serializable;

@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDTO implements Serializable {

	private static final long serialVersionUID = -4318666953157263434L;
	private String jwtToken;

}