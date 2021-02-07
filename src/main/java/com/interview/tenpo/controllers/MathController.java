package com.interview.tenpo.controllers;

import com.interview.tenpo.services.MathService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/math/")
@Api(tags = "math")
@CrossOrigin()
public class MathController {

    private final MathService mathService;

    @Validated
    @GetMapping(value = "/multiply")
    @ApiOperation(value = "${MathController.multiply}")
    public ResponseEntity<Double> multiply(@RequestParam(required = true)  @NotEmpty double value1,
                                           @RequestParam(required = true) @NotEmpty double value2) {
        return ResponseEntity.ok(mathService.multiply(value1 , value2));
    }
}