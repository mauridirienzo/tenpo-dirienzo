package com.interview.tenpo.controllers;

import com.interview.tenpo.entities.Trace;
import com.interview.tenpo.services.TraceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/trace/")
@Api(tags = "system")
public class TraceController {

    private final TraceService traceService;

    @Validated
    @GetMapping(value = "/system")
    @ApiOperation(value = "${TraceController.system}")
    public ResponseEntity<List<Trace>> getSystemTrace(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                                      @RequestParam(defaultValue = "id") String sortBy) {
        List<Trace> tracesResponse = traceService.getTraces(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(tracesResponse);
    }
}
