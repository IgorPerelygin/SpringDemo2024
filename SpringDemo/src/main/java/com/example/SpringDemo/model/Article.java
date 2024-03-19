package com.example.SpringDemo.model;

import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    @Column(name="content", columnDefinition = "TEXT")
    public String content;
    public String author;
    public Article(){}
    public Article(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
