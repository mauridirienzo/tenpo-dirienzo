package com.interview.tenpo.dto;

import com.interview.tenpo.security.ValidPassword;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserDataDTO {
    @NotNull(message = "User name may not be null")
    @NotEmpty(message = "User name not provided")
    @Size(min = 4 , message = "Username min size is 4")
    private String username;

    @Email(message = "Invalid e-mail")
    @NotNull(message = "Email may not be null")
    @NotEmpty(message = "Email not provided")
    private String email;

    @NotNull(message = "Password may not be null")
    @NotEmpty(message = "Password not provided")
    @ValidPassword
    private String password;

    @NotNull(message = "Confirmation Password may not be null")
    @NotEmpty(message = "Confirmation Password not provided")
    private String passwordConfirmation;
}