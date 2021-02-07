package com.interview.tenpo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MathService {
    public double multiply(double value1, double value2) {
        log.info("Init multiply for values: [{}],  [{}]", value1, value2);
        return value1 * value2;
    }
}
