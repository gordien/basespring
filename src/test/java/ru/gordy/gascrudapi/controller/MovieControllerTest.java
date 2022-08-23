package ru.gordy.gascrudapi.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MvcResult;
import ru.gordy.gascrudapi.entity.MovieEntity;
import ru.gordy.gascrudapi.service.MovieService;
import ru.gordy.gascrudapi.dao.MovieRepo;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @MockBean
    MovieRepo movieRepo;

    @Autowired
    MovieService movieService;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MovieController movieController;

    private final Long stubMovieId = 1L;

    private final String stubMovieTitle = "Avatar";
    private final Integer stubMovieYear = 2004;

    private final Long notExistStubMovieId = 9000000L;

    private MovieEntity getStubMovieInfo() {
        MovieEntity movie = new MovieEntity();
        movie.setId(stubMovieId);
        movie.setYear(stubMovieYear);
        movie.setTitle(stubMovieTitle);
        return movie;
    }

    private List<MovieEntity> getStubMovieListInfo() {

        List<MovieEntity> movieList = Stream.of(getStubMovieInfo()).collect(Collectors.toList());
        return movieList;
    }


    @BeforeEach
    void init() {
        Mockito.when(movieRepo.findAll()).thenReturn(getStubMovieListInfo());
        Mockito.when(movieRepo.findById(stubMovieId)).thenReturn(Optional.ofNullable(getStubMovieListInfo().get(0)));
        Mockito.when(movieRepo.existsById(stubMovieId)).thenReturn(true);
    }

    @Test
    void getMoviesTest() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/movies/")).andDo(print()).andExpect(status().isOk()).andReturn();
        Mockito.verify(movieRepo).findAll();
    }

    @Test
    void getMovieTest() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/movies/{id}", stubMovieId)).andDo(print()).andExpect(status().isOk()).andReturn();
        Mockito.verify(movieRepo).findById(stubMovieId);
    }

    @Test
    void getNonExistMovie() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/movies/{id}", notExistStubMovieId)).andDo(print()).andExpect(status().isNotFound()).andReturn();
        Mockito.verify(movieRepo).findById(notExistStubMovieId);
    }

    @Test
    void testDeleteMovie() throws Exception {
        MvcResult result = this.mockMvc.perform(delete("/api/movies/{id}", stubMovieId)).andDo(print()).andExpect(status().isOk()).andReturn();
        Mockito.verify(movieRepo).deleteById(stubMovieId);
    }


}
