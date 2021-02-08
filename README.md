# TENPO Spring Boot REST JWT 

![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack

![](https://img.shields.io/badge/java_15-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/postgrestsql-✓-blue.svg)
![](https://img.shields.io/badge/jwt-✓-blue.svg)
![](https://img.shields.io/badge/swagger_2-✓-blue.svg)
![](https://img.shields.io/badge/postman-✓-blue.svg)
![](https://img.shields.io/badge/maven-✓-blue.svg)
![](https://img.shields.io/badge/docker-✓-blue.svg)


## What is JSON Web Token?

JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA.

**Compact**: Because of their smaller size, JWTs can be sent through a URL, POST parameter, or inside an HTTP header. Additionally, the smaller size means transmission is fast.

**Self-contained**: The payload contains all the required information about the user, avoiding the need to query the database more than once.

## When should you use JSON Web Tokens?

Here are some scenarios where JSON Web Tokens are useful:

**Authentication**: This is the most common scenario for using JWT. Once the user is logged in, each subsequent request will include the JWT, allowing the user to access routes, services, and resources that are permitted with that token. Single Sign On is a feature that widely uses JWT nowadays, because of its small overhead and its ability to be easily used across different domains.

**Information Exchange**: JSON Web Tokens are a good way of securely transmitting information between parties. Because JWTs can be signed—for example, using public/private key pairs—you can be sure the senders are who they say they are. Additionally, as the signature is calculated using the header and the payload, you can also verify that the content hasn't been tampered with.


# JWT Authentication Summary

Token based authentication schema's became immensely popular in recent times, as they provide important benefits when compared to sessions/cookies:

- CORS
- No need for CSRF protection
- Better integration with mobile
- Reduced load on authorization server
- No need for distributed session store

# Implementation Details

Quick description of main application components.

## postgresql DB

This demo is currently using an postgresql database called **tenpo_test**. Note that `spring.jpa.hibernate.ddl-auto=create-drop` will drop and create a clean database each time the project is deployed (Only for testing).

## Core Code

1. `AuthenticationRequestFilter`
2. `JwtService`
3. `UserService`
4. `WebSecurityConfig`

**AuthenticationRequestFilter**

The `AuthenticationRequestFilter` filter is applied to each API (`/**`) with exception of followings endpoints:

"/swagger-resources/**",
"/v1/users/login",
"/v1/users/register",
"/swagger-ui.html",
"/swagger-ui/**",
"/v2/api-docs"

This filter has the following responsibilities:

1. Check for access token in Authorization header. If Access token is found in the header, delegate authentication to `JwtService` otherwise throw authentication exception
2. Invokes success or failure strategies based on the outcome of authentication process performed by JwtService

**JwtService**

The `JwtService` has the following responsibilities:

1. Verify the access token's signature
2. Extract identity and authorization claims from Access token and use them to create UserContext
3. If Access token is malformed, expired or simply if token is not signed with the appropriate signing key Authentication exception will be thrown

**UserService**

Implements `UserDetailsService` in order to define our own custom *loadUserbyUsername* function. The `UserDetailsService` interface is used to retrieve user-related data. It has one method named *loadUserByUsername* which finds a user entity based on the username and can be overridden to customize the process of finding the user.


**WebSecurityConfig**

The `WebSecurityConfig` class extends `WebSecurityConfigurerAdapter` to provide custom security configuration.


# How to use this code?

1. Make sure [Java 15](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html), [Maven](https://maven.apache.org) and [docker](https://docs.docker.com/) are installed

2. Fork this repository and clone it
  
```
$ git clone https://github.com/mauridirienzo/tenpo-dirienzo.git
```

3. Navigate to folder tenpo-di-rienzo 

```
$ cd tenpo-di-rienzo
```

4. Install dependencies

```
$ mvn install
```

5. Move to folder src/main/docker and run the project on docker containers using docker-compose. It will pull requiered images from dockerHub and application Will run (make sure port 8080 is not in use)

```
$ cd src/main/docker
docker-compose up
```

6. Navigate to `http://localhost:8080/swagger-ui/` in your browser in order to test APIs or use Postman using the following link:
https://www.getpostman.com/collections/6485d2177156c766a4f2.


