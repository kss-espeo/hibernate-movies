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

        List<Movie> movies = repository.readAll();

        System.out.println("Found following movies:");
        movies.forEach(m -> System.out.println(m.getTitle()));

        Movie someMovie = movies.get(0);
        someMovie.getReviews().forEach(r -> System.out.println(r.getContent()));

        HibernateConnector.shutdown();
    }

    private static void addMovies(MovieRepository repository) {
        Movie topGun = new Movie("Top Gun");
        topGun.setReviews(Arrays.asList(
                new Review("Rewelacyjny film!"),
                new Review("Emocjonujące walki w powietrzu")
        ));
        repository.save(topGun);

        Movie goldenEye = new Movie("Golden Eye");
        goldenEye.setReviews(Arrays.asList(
                new Review("Najsłabszy z Bondów")
        ));
        repository.save(goldenEye);

        Movie mammaMia = new Movie("Mamma Mia!");
        mammaMia.setReviews(Arrays.asList(
                new Review("Aż chce się tańczyć"),
                new Review("Mamma Mia! Here I go again!")
        ));
        repository.save(mammaMia);
    }
}
