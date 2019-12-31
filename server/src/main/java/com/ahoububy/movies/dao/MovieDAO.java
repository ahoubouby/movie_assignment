package com.ahoububy.movies.dao;

import com.ahoububy.movies.models.Movie;
import com.ahoububy.movies.models.TypeMovie;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieDAO {
    Movie insertMovie(Movie movie);

    Set<Movie> findAllMovies();

    Optional<Movie> findByName(String name);

    Movie updateMovie(Movie movie);

    boolean deleteMovie(Movie movie);
}
