package com.interview.tenpo.repositories;

import com.interview.tenpo.entities.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.Collections;
import java.util.List;


public class RemoteHttpTraceRepository implements HttpTraceRepository {

    @Autowired
    private TraceCustomRepository traceRepository;

    public RemoteHttpTraceRepository(){

    }

    @Override
    public List<HttpTrace> findAll() {
        return Collections.emptyList();
    }
 
    @Override
    public void add(HttpTrace trace) {
        Trace traceDTO = new Trace();
        traceDTO.setEndpoint(trace.getRequest().getUri().toString());
        traceDTO.setResponseCode(trace.getResponse().getStatus());
        traceDTO.setTimeStamp(trace.getTimestamp().toString());
        traceRepository.save(traceDTO);
    }
}
