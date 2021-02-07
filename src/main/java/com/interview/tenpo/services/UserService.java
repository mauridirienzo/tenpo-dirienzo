package com.interview.tenpo.services;

import com.interview.tenpo.dto.UserDataDTO;
import com.interview.tenpo.entities.User;
import com.interview.tenpo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Finding user {}", username);
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		log.info("user {} has been found", username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

	public boolean isUserRegistered(String username){
		User user = userRepository.findByUsername(username);
		return (user != null);
	}

	public User save(UserDataDTO user) {
		User newUser = User.builder()
				.email(user.getEmail())
				.username(user.getUsername())
				.password(bcryptEncoder.encode(user.getPassword()))
				.build();
		return userRepository.save(newUser);
	}
}