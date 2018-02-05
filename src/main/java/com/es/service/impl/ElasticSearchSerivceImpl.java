package com.es.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.es.dao.ElasticSearchDao;
import com.es.model.Books;
import com.es.model.Law;
import com.es.service.ElasticSearchService;

@Service
public class ElasticSearchSerivceImpl implements ElasticSearchService {

    @Autowired
    private ElasticSearchDao elasticSearchDao;

    @Override
    public List<Books> getLawData(String name, Integer start, Integer size) {
        List<Books> books = elasticSearchDao.getLawData(name, start, size);
        return books;
    }

}
