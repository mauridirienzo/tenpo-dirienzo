package com.interview.tenpo.model;

import lombok.*;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.security.core.Authentication;

@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentTrace {

    protected HttpTrace httpTrace;
    protected String requestBody;
    protected String responseBody;
    protected Authentication principal;
}
