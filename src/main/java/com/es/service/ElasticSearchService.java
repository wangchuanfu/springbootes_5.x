package com.es.service;

import java.util.List;

import com.es.model.Books;
import com.es.model.Law;

public interface ElasticSearchService {

    public List<Books> getLawData(String name, Integer start, Integer size);

}
