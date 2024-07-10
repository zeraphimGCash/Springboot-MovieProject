package com.mynt.MovieProjectApiJCDiamante.Controller;

import com.mynt.MovieProjectApiJCDiamante.Enum.Genre;
import com.mynt.MovieProjectApiJCDiamante.Model.Movie;
import com.mynt.MovieProjectApiJCDiamante.Repository.GenreRepository;
import com.mynt.MovieProjectApiJCDiamante.Repository.MovieRepository;
import com.mynt.MovieProjectApiJCDiamante.Service.GenreService;
import com.mynt.MovieProjectApiJCDiamante.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;
    GenreService genreService;
    MovieRepository movieRepository;

    // Root / Index endpoint, contains all of the possible endpoints that can be accessed with the API.
    @GetMapping("/")
    public Map<String, String> indexMovie() {
        HashMap<String, String> map = new HashMap<>();
        map.put("/movies", "Returns all movies in the database in JSON format");
        map.put("/getMoviesByYear/{yearReleased}", "Returns movies in the database filtered by their year released  in JSON format");
        map.put("/getAllMoviesOrderByYearReleased", "Returns all movies in the database sorted in ascending order in JSON format");
        map.put("/getMoviesByGenreAndNotIsSequel/{genres}&{isSequel}", "Returns movies in the database based on their genre and if they have a sequel in JSON format");
        return map;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

//    /getMoviesByYear/{i}
    @GetMapping("/getMoviesByYear/{yearReleased}")
    public List<Movie> getMoviesByYear(@PathVariable("yearReleased") Integer yearReleased){
        return movieService.getMoviesByYear(yearReleased);
    }

    @GetMapping("/getAllMoviesOrderByYearReleased")
    public List<Movie> getAllMoviesOrderByYearReleased() {
        return movieService.getAllMoviesOrderByYearReleased();
    }

    @GetMapping("/getMoviesByGenreAndNotIsSequel/{genre}&{isSequel}")
    public List<Movie> getMoviesByGenreAndNotIsSequel(@PathVariable("genre") Long genreId, @PathVariable("isSequel") boolean isSequel) {
        return movieService.getMoviesByGenreAndNotIsSequel(mapGenreIdToEnum(genreId), isSequel);
    }

    private Genre mapGenreIdToEnum(Long genreId) {
        return switch (genreId.intValue()) {
            case 1 -> Genre.ACTION;
            case 2 -> Genre.COMEDY;
            case 3 -> Genre.ROMANCE;
            case 4 -> Genre.HORROR;
            case 5 -> Genre.FANTASY;
            default -> throw new IllegalArgumentException("Invalid genre ID: " + genreId);
        };
    }

}
