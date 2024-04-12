package com.example.SpringDemo.repo;

import com.example.SpringDemo.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
    List<Comment> findByArticleId(Long id);
}






