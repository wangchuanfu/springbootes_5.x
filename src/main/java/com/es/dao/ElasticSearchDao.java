package com.es.dao;

import java.util.List;

import com.es.model.Books;
import com.es.model.Law;

public interface ElasticSearchDao {

    List<Books> getLawData(String name, Integer start, Integer size);

}
