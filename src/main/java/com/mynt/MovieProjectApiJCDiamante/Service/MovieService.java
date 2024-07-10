package com.mynt.MovieProjectApiJCDiamante.Service;

import com.mynt.MovieProjectApiJCDiamante.Enum.Genre;
import com.mynt.MovieProjectApiJCDiamante.Model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie saveMovie(Movie movie);
    List<Movie> getMoviesByYear(int yearReleased);
    List<Movie>  getAllMoviesOrderByYearReleased();
    List<Movie> getMoviesByGenreAndNotIsSequel(Genre genre, Boolean isSequel);
}
