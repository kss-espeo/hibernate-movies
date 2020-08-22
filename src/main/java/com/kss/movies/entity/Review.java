package com.kss.movies.entity;

import javax.persistence.*;

@Entity(name = "Review")
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue
    private Integer id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review )) return false;
        return id != null && id.equals(((Review) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
