package com.ahoububy.movies.api.v1.controllers;

import com.ahoububy.movies.dto.MovieDTO;
import com.ahoububy.movies.exceptions.NotFoundException;
import com.ahoububy.movies.models.TypeMovie;
import com.ahoububy.movies.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping()
    public Set<MovieDTO> findAll() {
        return movieService.findAllMovies();
    }

    @GetMapping("/{name}")
    public MovieDTO findByName(@PathVariable String name) {
        Optional<MovieDTO> result = movieService.findByName(name);
        if (!result.isPresent())
            throw new NotFoundException("no movie found by that's name");
        return result.get();
    }

    @GetMapping("/type/{type}")
    public Set<MovieDTO> byType(@PathVariable String type) {
        return movieService.findByType(TypeMovie.valueOf(type.toUpperCase()));
    }

    @DeleteMapping
    public boolean deleteMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.deleteMovie(movieDTO);
    }

    @PostMapping
    public MovieDTO addNewMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.insertMovie(movieDTO);
    }
}
