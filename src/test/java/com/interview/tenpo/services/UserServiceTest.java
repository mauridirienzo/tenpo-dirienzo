package com.interview.tenpo.services;

import com.interview.tenpo.entities.User;
import com.interview.tenpo.repositories.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserService target;
    private UserRepository userRepository;
    private  PasswordEncoder bcryptEncoder;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        bcryptEncoder = mock(PasswordEncoder.class);
        target = new UserService(userRepository, bcryptEncoder);
    }

    @Test
    void loadUserByUsernameOk() {
        String userName = "testUser";
        User user = mock(User.class);
        when(userRepository.findByUsername(eq(userName))).thenReturn(user);
        when(user.getPassword()).thenReturn("password");
        when(user.getUsername()).thenReturn(userName);

        UserDetails resultUser = target.loadUserByUsername(userName);
        assertEquals(resultUser.getUsername(), userName);
    }

    @Test
    void loadUserByUsernameFail() {
        String username = "testUser";
        User user = mock(User.class);
        when(userRepository.findByUsername(eq(username))).thenReturn(null);

        Exception exception = assertThrows(UsernameNotFoundException.class,() ->  target.loadUserByUsername(username));

        Assertions.assertTrue(exception instanceof UsernameNotFoundException);
        assertEquals(exception.getMessage(),"User not found with username: " + username);
    }
}
