package com.kss.movies.entity;

import javax.persistence.*;

@Entity(name = "Review")
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue
    private Integer id;

    private String content;

    public Review() {

    }

    public Review(String content) {
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String name) {
        this.content = name;
    }
}
