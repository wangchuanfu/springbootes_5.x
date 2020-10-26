package com.es.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.es.model.Books;
import com.es.model.Law;
import com.es.service.ElasticSearchService;

@RestController
public class BooksController {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping(value = "/books/books")
    public List<Books> findByName(@RequestParam(value = "title") String title,
            @RequestParam(value = "start", defaultValue = "1") Integer start,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        List<Books> books = elasticSearchService.getLawData(title, start - 1, size);
        return books;
    }
//测试 撤销push 操作

}
