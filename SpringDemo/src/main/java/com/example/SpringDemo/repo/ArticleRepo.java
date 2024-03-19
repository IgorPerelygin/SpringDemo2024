package com.example.SpringDemo.repo;

import com.example.SpringDemo.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ArticleRepo extends CrudRepository<Article, Long> {

}
