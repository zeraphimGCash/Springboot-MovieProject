package com.mynt.MovieProjectApiJCDiamante.Service;

import com.mynt.MovieProjectApiJCDiamante.Enum.Genre;
import com.mynt.MovieProjectApiJCDiamante.Model.Movie;
import com.mynt.MovieProjectApiJCDiamante.Repository.GenreRepository;
import com.mynt.MovieProjectApiJCDiamante.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;
    GenreRepository genreRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

//    @Override
//    public List<Movie> getAllMovie() {
//        List<Movie> movies = new ArrayList<Movie>();
//        movieRepository.findAll().forEach(movie -> movies.add(movie));
//
//        return movies;
//    }

    @Override
    public List<Movie> getAllMovies() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesByYear(int yearReleased) {
        List<Movie> moviesByYear = new ArrayList<>();
        for (Movie movie : movieRepository.findAll()) {
            if (movie.getYearReleased() == yearReleased) {
                moviesByYear.add(movie);
            }
        }
        return moviesByYear;
    }

    @Override
    public List<Movie> getAllMoviesOrderByYearReleased() {
        List<Movie> allMovies = new ArrayList<>();
        for (Movie movie : movieRepository.findAll()) {
            allMovies.add(movie);
        }
        allMovies.sort(Comparator.comparingInt(Movie::getYearReleased));
        return allMovies;
    }

    @Override
    public List<Movie> getMoviesByGenreAndNotIsSequel(Genre genre, Boolean isSequel) {
        // Assuming you have a movieRepository or some data source
        List<Movie> allMovies = new ArrayList<>();
        for (Movie movie : movieRepository.findAll()){
            if (Objects.equals(movie.getGenre().getGenre_name(), genre.toString()) && movie.getIsSequel() == isSequel) {
                allMovies.add(movie);
            }
        }
        return allMovies;
    }

    //@PreAuthorize("isAuthenticated()")
    //@PreAuthorize("hasAuthority('DEV_USER')")
    //@PreAuthorize("hasAnyRole('ROLE_USER')")
    @Override
    public String getSecurity() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authenticated user is the MOCK USER and has ROLE_USER
        if (auth != null && auth.getName().equals("admin") && !auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"))) {
            throw new AccessDeniedException("Access Denied");
        }

        return "" + auth;
    }

//    @PreAuthorize("hasRole('ROLE_USER') and principal.username == 'admin'")
//    @Override
//    public String getSecurity() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return "" + auth;
//    }

}
