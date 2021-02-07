package com.interview.tenpo.services;

import com.interview.tenpo.entities.Trace;
import com.interview.tenpo.repositories.TraceCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TraceService {

    private final TraceCustomRepository traceRepository;

    public TraceService(TraceCustomRepository traceRepository) {
        this.traceRepository = traceRepository;
    }

    public List<Trace> getTraces(Integer pageNumber, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        Page<Trace> pagedResult = traceRepository.findAll(paging);

        return pagedResult.hasContent() ? pagedResult.getContent() : List.of();
    }
}
