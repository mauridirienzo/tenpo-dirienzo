package com.interview.tenpo.repositories;

import com.interview.tenpo.entities.Trace;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TraceCustomRepository extends PagingAndSortingRepository<Trace, Long> {
}
