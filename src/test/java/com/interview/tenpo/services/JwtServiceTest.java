package com.interview.tenpo.services;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtServiceTest {

    private JwtService target;
    private Authentication authentication;
    private String testUser = "testuser";
    private String secret = "testsecret";

    @BeforeEach
    public void setup() {
        target = new JwtService(secret);
        authentication = mock(Authentication.class);
    }

    @Test
    void getUsernameFromToken() {
        String testToken = buildJwt(testUser,new Date(System.currentTimeMillis() + 60000));
        String actualUser = target.getUsernameFromToken(testToken);
        assertEquals(testUser, actualUser);
    }

    @Test
    void getExpirationDateFromToken() {
        Date expectedExpirationDate = new Date(System.currentTimeMillis() + 60000);
        String testToken = buildJwt(testUser,expectedExpirationDate);
        Date actualExpirationDate = target.getExpirationDateFromToken(testToken);
        assertEquals(actualExpirationDate.getHours(), expectedExpirationDate.getHours());
        assertEquals(actualExpirationDate.getMinutes(), expectedExpirationDate.getMinutes());
        assertEquals(actualExpirationDate.getSeconds(), expectedExpirationDate.getSeconds());
    }

    @Test
    void generateToken() {
        GrantedAuthority grantedAuthority = mock(GrantedAuthority.class);
        Collection grantedAuthorityList = new ArrayList<>();

        UserDetails userDetails = mock(UserDetails.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(testUser);
        when(grantedAuthority.getAuthority()).thenReturn("testRole");
        when(authentication.getAuthorities()).thenReturn(grantedAuthorityList);
        String actualToken = target.generateToken(authentication);
        assertNotNull(actualToken);
    }

    @Test
    void validateTokenOk() {
        String testToken = buildJwt(testUser,new Date(System.currentTimeMillis() + 60000));
        target.getJwtStoreMap().put(testUser,testToken);
        boolean result = target.validateToken(testToken);
        assertTrue(result);
    }

    @Test
    void validateTokenFail() {
        String testToken = buildJwt(testUser,new Date(System.currentTimeMillis() - 100));
        target.getJwtStoreMap().put(testUser,testToken);
        Exception exception = assertThrows(ExpiredJwtException.class,() ->  target.validateToken(testToken));

        assertTrue(exception instanceof ExpiredJwtException);
    }

    private String buildJwt(String userName, Date expirationDate){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}