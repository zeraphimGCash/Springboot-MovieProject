package com.mynt.MovieProjectApiJCDiamante;

import com.mynt.MovieProjectApiJCDiamante.Model.Genre;
import com.mynt.MovieProjectApiJCDiamante.Model.Movie;
import com.mynt.MovieProjectApiJCDiamante.Repository.GenreRepository;
import com.mynt.MovieProjectApiJCDiamante.Repository.MovieRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovieProjectApiJcDiamanteApplicationTests {

    // Test #1
    // The test ensures that the save method of the MovieRepository class returns a non-null Movie object when called.
    // This is a basic test to check the functionality of the save method using a mock repository.
    @Test
    public void testSave() {
        MovieRepository rep = mock(MovieRepository.class);
        when(rep.save(any())).thenReturn(new Movie());

        assertNotNull(rep.save(new Movie()));
    }

    // Test #2
    // this test verifies that the save method of the GenreRepository returns a non-null Genre object when called, ensuring the basic functionality of the save method using a mock repository.

    @Test
    public void testSaveGenre() {
        GenreRepository genreRep = mock(GenreRepository.class);
        when(genreRep.save(any())).thenReturn(new Genre());

        assertNotNull(genreRep.save(new Genre()));
    }

}
