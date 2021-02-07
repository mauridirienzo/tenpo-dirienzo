package com.interview.tenpo.services;

import com.interview.tenpo.entities.Trace;
import com.interview.tenpo.repositories.TraceCustomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TraceServiceTest {
    private TraceService target;
    private TraceCustomRepository traceRepository;

    @BeforeEach
    public void setup() {
        traceRepository = mock(TraceCustomRepository.class);
        target = new TraceService(traceRepository);
    }

    @Test
    void getTraces() {
        Trace trace = mock(Trace.class);
        Page<Trace> traces = mock(Page.class);
        when(traceRepository.findAll(any(Pageable.class))).thenReturn(traces);
        when(traces.hasContent()).thenReturn(true);
        when(traces.getContent()).thenReturn(List.of(trace));

        List<Trace> results = target.getTraces(1,3,"id");
        assertEquals(results.size(),1);
        assertEquals(results.get(0), trace);
    }

    @Test
    void getTracesEmpty() {
        Page<Trace> traces = mock(Page.class);
        when(traceRepository.findAll(any(Pageable.class))).thenReturn(traces);
        when(traces.hasContent()).thenReturn(true);
        when(traces.getContent()).thenReturn(List.of());

        List<Trace> results = target.getTraces(1,3,"id");
        assertEquals(results.size(),0);
    }
}