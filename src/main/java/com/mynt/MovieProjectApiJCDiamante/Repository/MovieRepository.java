package com.mynt.MovieProjectApiJCDiamante.Repository;

import com.mynt.MovieProjectApiJCDiamante.Model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long>{
//    List<Movie> getMoviesByYear(int year);
//    Movie saveMovie(Movie movie);
//    List<Movie> getMoviesByYear(int yearReleased);
//    List<Movie>  getAllMoviesOrderByYearReleased();
//    List<Movie> getMoviesByGenreAndNotIsSequel();
}
