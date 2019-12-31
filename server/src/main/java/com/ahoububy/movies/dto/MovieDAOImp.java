package com.ahoububy.movies.dto;


import com.ahoububy.movies.dao.MovieDAO;
import com.ahoububy.movies.models.Movie;
import com.ahoububy.movies.models.TypeMovie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class MovieDAOImp implements MovieDAO {
    @Value("${data.file}")
    String filePath;
    @Autowired
    private ObjectMapper mapper;

    private static final String TAG = MovieDAOImp.class.getSimpleName();

    @Override
    public Movie insertMovie(@NotNull Movie movie) {
        Movie inseted;
        Set<Movie> movies = findAllMovies();
        movies.add(movie);
        try {
            this.mapper.writeValue(new FileOutputStream(filePath), movies);
            inseted = movie;
        } catch (IOException ex) {
            ex.printStackTrace();
            inseted = null;
        }
        return inseted;
    }

    @Override
    public Set<Movie> findAllMovies() {
        Set<Movie> movies;
        try {
            movies = mapper.readValue(new File(this.filePath), new TypeReference<Set<Movie>>() {
            });
        } catch (IOException excption) {
            log.error("{} findAllMovies {}", TAG, excption.getMessage());
            movies = null;
        }

        return movies;
    }

    @Override
    public Optional<Movie> findByName(String name) {
        Optional<Movie> result;
        try {
            List<Movie> movies = mapper.readValue(new File(this.filePath), new TypeReference<Set<Movie>>() {
            }).stream().filter(movie -> movie.getTitle().equals(name)).collect(Collectors.toList());
            result = movies.size() > 0 ? Optional.of(movies.get(0)) : Optional.empty();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return null;
    }

    @Override
    public boolean deleteMovie(Movie movie) {
        boolean result;
        try {
            Set<Movie> movies = findAllMovies()
                    .stream()
                    .filter(m -> !m.equals(movie))
                    .collect(Collectors.toSet());
            this.mapper.writeValue(new FileOutputStream(this.filePath), movies);
            result = true;
        } catch (IOException ex) {
            result = false;
        }

        return result;
    }
}
