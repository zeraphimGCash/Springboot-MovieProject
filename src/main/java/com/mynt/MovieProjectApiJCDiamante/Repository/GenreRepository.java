package com.mynt.MovieProjectApiJCDiamante.Repository;

import com.mynt.MovieProjectApiJCDiamante.Model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre getGenreById(Long genreId);
}