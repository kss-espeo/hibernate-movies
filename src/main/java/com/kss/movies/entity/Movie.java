package com.kss.movies.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Movie")
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Review> reviews = new ArrayList<>();

    protected Movie() {

    }

    public Movie(String title) {
        this.title = title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setMovie(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setMovie(null);
    }
}
