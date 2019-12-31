package com.ahoububy.movies.services;

import com.ahoububy.movies.dto.MovieDTO;
import com.ahoububy.movies.models.TypeMovie;

import java.util.Optional;
import java.util.Set;

public interface MovieService {
    Set<MovieDTO> findAllMovies();

    Optional<MovieDTO> findByName(String name);

    Set<MovieDTO> findByType(TypeMovie type);

    boolean deleteMovie(MovieDTO movieDTO);

    MovieDTO insertMovie(MovieDTO movieDTO);

    Optional<MovieDTO> update(MovieDTO movieDTO);
}
