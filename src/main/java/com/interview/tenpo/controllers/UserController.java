package com.interview.tenpo.controllers;

import com.interview.tenpo.entities.User;
import com.interview.tenpo.exceptions.PasswordMissmatchException;
import com.interview.tenpo.exceptions.UserAlreadyRegisteredException;
import com.interview.tenpo.dto.JwtRequestDTO;
import com.interview.tenpo.dto.JwtResponseDTO;
import com.interview.tenpo.dto.UserDataDTO;
import com.interview.tenpo.services.JwtService;
import com.interview.tenpo.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@CrossOrigin
@Api(tags = "users")
@RequestMapping("/v1/users/")
public class UserController {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserService userDetailsService;

	@PostMapping(value = "/login")
	@ApiOperation(value = "${UserController.login}")
	public ResponseEntity<JwtResponseDTO> loginUser(@RequestBody JwtRequestDTO authenticationRequest) {
		final Authentication auth = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return ResponseEntity.ok(new JwtResponseDTO(jwtService.generateToken(auth)));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDataDTO user) throws UserAlreadyRegisteredException {

		if(userDetailsService.isUserRegistered(user.getUsername().trim())){
			throw new UserAlreadyRegisteredException("User is already registered");
		}else if(user.getPassword().trim().equals(user.getPasswordConfirmation().trim())){
			return ResponseEntity.ok(userDetailsService.save(user));
		}else{
			throw new PasswordMissmatchException("Password and confirmation password must match");
		}
	}

	private Authentication authenticate(String username, String password) {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new DisabledException("User is not active");
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Credentials are not valid");
		}
	}
}