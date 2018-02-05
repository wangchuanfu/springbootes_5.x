package com.es.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilder;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.es.dao.ElasticSearchDao;
import com.es.model.Books;
import com.es.model.Law;
import com.es.util.JsonUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class ElasticSearchDaoImpl implements ElasticSearchDao {
    @Autowired
    private TransportClient transportClient;
	private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Books> getLawData(String title, Integer start, Integer size) {
     //   log.info("getTestData start...");
		 QueryBuilder matchQuery = QueryBuilders.matchQuery("title", title);
			SearchResponse response =transportClient.prepareSearch("books").setTypes("books").setQuery(matchQuery).execute().actionGet();
			SearchHit[] hitss =response.getHits().getHits();
       // SearchResponse searchresponse = transportClient.prepareSearch("books").setTypes("books")
                //.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)// 结构化查询
        //        .setQuery(QueryBuilders.matchQuery("title", title)).get();//.setFrom(start).setSize(size).setExplain(true)// 分页返回
                // matchQuery 分词检索，termQuery不分词
                // .setQuery(QueryBuilders.termQuery("name", "中国环境"))
                // .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18)) //对搜索结果进行过滤
              //  .addSort("id", SortOrder.ASC).get();
        SearchHit[] hits = response.getHits().getHits();
        /**
         * 
      
        for (int i = 0; i < hits.length; i++) {
            SearchHit searchHit = hits[i];
            String string = searchHit.getSourceAsString();
            Law law = JsonUtils.jsonToPojo(string, Law.class);
            laws.add(law);

        }   */
        List<Books> list=new ArrayList<Books>();
		for(SearchHit hit: hits){
			
			Books books;
			try {
				books = mapper.readValue(hit.getSourceAsString(), Books.class);
				list.add(books);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//transportClient.close();
		return list;
	  }


}
