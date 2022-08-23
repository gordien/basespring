package ru.gordy.gascrudapi.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.gordy.gascrudapi.mapper.MovieMapper;
import ru.gordy.gascrudapi.annotation.Benchmark;
import ru.gordy.gascrudapi.dao.MovieRepo;

import ru.gordy.gascrudapi.dto.MovieCreateRequestDto;
import ru.gordy.gascrudapi.dto.MovieResponseDto;
import ru.gordy.gascrudapi.entity.MovieEntity;
import ru.gordy.gascrudapi.exception.MovieNotFoundException;
import ru.gordy.gascrudapi.exception.MovieAlreadyExistException;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieService {

    MovieRepo movieRepo;

   MovieMapper movieMapper;


    @Benchmark
    public MovieResponseDto addMovie(MovieCreateRequestDto movie) {

        if (movieRepo.findByTitleAndYear(movie.getTitle(), movie.getYear()) != null) {
            throw new MovieAlreadyExistException();
        }
        MovieEntity newMovie = movieMapper.mapRequestToMovieEntity(movie);
        MovieEntity addedMovie =  movieRepo.save(newMovie);
        MovieResponseDto addedMovieResponse = movieMapper.mapToMovieResponseDto(addedMovie);

        return addedMovieResponse;



    }

    public MovieResponseDto patchMovie(Long id, MovieCreateRequestDto updatedMovie) throws MovieNotFoundException {

            MovieEntity movie = movieRepo.findById(id).orElseThrow(()-> new MovieNotFoundException());
            movie.setTitle((updatedMovie.getTitle() != null)? updatedMovie.getTitle() : movie.getTitle());
            movie.setYear((updatedMovie.getYear()!= null)? updatedMovie.getYear(): movie.getYear());
            movie.setViewed((updatedMovie.getViewed()!=null) ? updatedMovie.getViewed():movie.getViewed() );
            movieRepo.save(movie);

            return movieMapper.mapToMovieResponseDto(movie);

    }

    public MovieResponseDto updateMovie(Long id, MovieCreateRequestDto updatedMovie) throws MovieNotFoundException {
        MovieEntity movie = movieRepo.findById(id).orElseThrow(()->new MovieNotFoundException());
        movie.setTitle(updatedMovie.getTitle());
        movie.setYear(updatedMovie.getYear());
        movie.setViewed(updatedMovie.getViewed());
        movie.setAuthor(updatedMovie.getUser());
        movieRepo.save(movie);
        return  movieMapper.mapToMovieResponseDto(movie);

    }
    @Benchmark
    public MovieResponseDto getMovie(Long id) {
           MovieEntity movie = movieRepo.findById(id).orElseThrow(()-> new MovieNotFoundException());
            return movieMapper.mapToMovieResponseDto(movie);
           }


    public List<MovieResponseDto> getMovies() {
        return movieRepo.findAll().stream().map(movieMapper::mapToMovieResponseDto).collect(Collectors.toList());
    }

    public void deleteMovie(Long id) {
        movieRepo.findById(id).orElseThrow(()-> new MovieNotFoundException());
        movieRepo.deleteById(id);

    }


}
