package com.interview.tenpo.dto;

import lombok.*;

import java.io.Serializable;

@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestDTO implements Serializable {

	private static final long serialVersionUID = -5794137589507048144L;
	private String username;
	private String password;

}