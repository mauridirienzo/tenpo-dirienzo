package com.interview.tenpo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MathServiceTest {


    private MathService target;

    @BeforeEach
    public void setup() {
        target = new MathService();
    }

    @Test
    void multiplyOk() {
       double result = target.multiply(20, 4);
       assertEquals(result, 80);
    }

    void multiplyFail() {
        double result = target.multiply(20, 4);
        assertEquals(result, 80);
    }
}