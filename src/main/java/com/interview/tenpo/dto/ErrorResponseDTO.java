package com.interview.tenpo.dto;

import lombok.*;

import java.util.List;

@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO
{
    private String message;
    private List<String> details;
}