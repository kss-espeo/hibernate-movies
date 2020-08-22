package com.kss.movies;

import com.kss.movies.db.HibernateConnector;
import com.kss.movies.db.MovieRepository;
import com.kss.movies.entity.Review;
import com.kss.movies.entity.Movie;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieRepository repository = new MovieRepository();

        addMovies(repository);

        repository.delete("Golden Eye");

        List<Movie> movies = repository.readAll();

        System.out.println("Found following movies:");
        movies.forEach(m -> System.out.println(m.getTitle()));

        Movie topGun = movies.stream().filter(m -> "Top Gun".equals(m.getTitle())).findFirst().get();
        System.out.println("Reviews for Top Gun:");
        topGun.getReviews().forEach(r -> System.out.println(r.getContent()));

        HibernateConnector.shutdown();
    }

    private static void addMovies(MovieRepository repository) {
        Movie topGun = new Movie("Top Gun");
        topGun.addReview(new Review("Niezły film!"));
        topGun.addReview(new Review("Emocjonujące walki w powietrzu"));
        topGun.addReview(new Review("Zawsze chciałem być pilotem..."));
        repository.save(topGun);

        Movie goldenEye = new Movie("Golden Eye");
        goldenEye.addReview(new Review("Najsłabszy z Bondów"));
        repository.save(goldenEye);

        Movie mammaMia = new Movie("Mamma Mia!");
        mammaMia.addReview(new Review("Aż chce się tańczyć"));
        mammaMia.addReview(new Review("Mamma Mia! Here I go again!"));
        repository.save(mammaMia);
    }
}
