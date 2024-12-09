package com.mynt.MovieProjectApiJCDiamante;

import com.mynt.MovieProjectApiJCDiamante.Model.Genre;
import com.mynt.MovieProjectApiJCDiamante.Model.Movie;
import com.mynt.MovieProjectApiJCDiamante.Repository.GenreRepository;
import com.mynt.MovieProjectApiJCDiamante.Repository.MovieRepository;
import com.mynt.MovieProjectApiJCDiamante.Service.MovieService;
import com.mynt.MovieProjectApiJCDiamante.Service.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@EnableWebSecurity
@AutoConfigureMockMvc
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enable method-level security

class MovieProjectApiJcDiamanteApplicationTests {

    //@Autowired
    //MovieService service;
    MovieService service = new MovieServiceImpl();

    // [MovieRepository TestCases]

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

    // Testing the findAll method of MovieRepository. We're mocking the method to return a list of Movie objects, and then asserting that the result is not null.

    @Test
    public void testFindAll() {
        MovieRepository movieRep = mock(MovieRepository.class);
        when(movieRep.findAll()).thenReturn(java.util.Collections.singletonList(new Movie()));

        assertNotNull(movieRep.findAll());
    }

    // Test #3
    // Testing the findById method of MovieRepository. We're mocking the method to return a Movie object
    @Test
    public void testDeleteById() {
        MovieRepository movieRep = mock(MovieRepository.class);
        movieRep.deleteById(1L); // assuming the id is of type Long
        verify(movieRep, times(1)).deleteById(1L);
    }

    // [GenreRepository Test Cases]

    // Test #4
    // this test verifies that the save method of the GenreRepository returns a non-null Genre object when called, ensuring the basic functionality of the save method using a mock repository.

    @Test
    public void testSaveGenre() {
        GenreRepository genreRep = mock(GenreRepository.class);
        when(genreRep.save(any())).thenReturn(new Genre());

        assertNotNull(genreRep.save(new Genre()));
    }

    // Test #5
    // this test verifies that the findAll method of the GenreRepository returns a non-null list of Genre
    // objects when called, ensuring the basic functionality of the findAll method using a mock repository.
    @Test
    public void testFindAllGenre() {
        GenreRepository genreRep = mock(GenreRepository.class);
        when(genreRep.findAll()).thenReturn(java.util.Collections.singletonList(new Genre()));
        assertNotNull(genreRep.findAll());
    }

    // Test #6
    // this test verifies that the deleteById method of the GenreRepository deletes a Genre object with the
    // specified id when called, ensuring the basic functionality of the deleteById method using a mock repository
    @Test
    public void testDeleteByIdGenre() {
        GenreRepository genreRep = mock(GenreRepository.class);
        genreRep.deleteById(1L); // assuming the id is of type Long
        verify(genreRep, times(1)).deleteById(1L);
    }

    @Test
    @WithMockUser("admin")
    public void testGetSecurity() {
        System.out.println(service.getSecurity());
        assertNotNull(service.getSecurity());
    }

    @Test
    public void testGetSecurityNegative() {
        System.out.println(service.getSecurity());
        assertNotNull(service.getSecurity());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"DEVELOPER"})
    public void testGetSecurityWithDevUser() {
        assertThrows(AccessDeniedException.class, () -> service.getSecurity());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"}) // Simulate "ROLE_USER"
    public void testGetSecurityWithRoleUser() {
        // Should pass since "ROLE_USER" is allowed
        String securityDetails = service.getSecurity();
        assertNotNull(securityDetails);
    }
}
