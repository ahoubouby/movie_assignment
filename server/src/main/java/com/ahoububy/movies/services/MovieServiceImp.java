package com.ahoububy.movies.services;

import com.ahoububy.movies.dao.MovieDAO;
import com.ahoububy.movies.dto.MovieDTO;
import com.ahoububy.movies.models.Movie;
import com.ahoububy.movies.models.TypeMovie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImp implements MovieService {
    private MovieDAO movieDAO;

    public MovieServiceImp(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    private Optional<MovieDTO> mapper(Movie movie) throws BeansException {
        MovieDTO result = new MovieDTO();
        BeanUtils.copyProperties(movie, result);
        return Optional.of(result);
    }

    private MovieDTO mapperV(Movie movie) throws BeansException {
        MovieDTO result = new MovieDTO();
        BeanUtils.copyProperties(movie, result);
        return (result);
    }

    private Movie mapper(MovieDTO movieDTO) throws BeansException {
        Movie result = new Movie();
        BeanUtils.copyProperties(movieDTO, result);
        return result;
    }

    @Override
    public Set<MovieDTO> findAllMovies() throws BeansException {
        return movieDAO.findAllMovies()
                .stream()
                .map(this::mapperV)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<MovieDTO> findByName(String name) {
        Optional<Movie> movie = movieDAO.findByName(name);
        Optional<MovieDTO> result;
        try {
            result = movie.flatMap(this::mapper);
        } catch (BeansException ex) {
            result = Optional.empty();
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Set<MovieDTO> findByType(TypeMovie type) {
        return movieDAO.findAllMovies()
                .stream()
                .filter(movie -> movie.getType().equals(type))
                .map(this::mapperV)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean deleteMovie(MovieDTO movieDTO) {
        boolean result = movieDAO.deleteMovie(this.mapper(movieDTO));
        return result;
    }

    @Override
    public MovieDTO insertMovie(MovieDTO movieDTO) {
        return this.mapperV(movieDAO.insertMovie(mapper(movieDTO)));
    }

    @Override
    public Optional<MovieDTO> update(MovieDTO movieDTO) {
        return Optional.empty();
    }
}
