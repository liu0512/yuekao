package com.liu.cms.respository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.liu.cms.pojo.Article;

public interface ArticleRepositroy extends ElasticsearchRepository<Article, Integer>{

}
