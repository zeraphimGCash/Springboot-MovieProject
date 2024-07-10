package com.mynt.MovieProjectApiJCDiamante.Service;

import com.mynt.MovieProjectApiJCDiamante.Model.Genre;
import com.mynt.MovieProjectApiJCDiamante.Model.Movie;
import com.mynt.MovieProjectApiJCDiamante.Repository.GenreRepository;
import com.mynt.MovieProjectApiJCDiamante.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre getGenreById(Long genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }
}
