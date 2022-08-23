package ru.gordy.gascrudapi.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gordy.gascrudapi.service.MovieService;
import ru.gordy.gascrudapi.dto.MovieCreateRequestDto;
import ru.gordy.gascrudapi.dto.MovieResponseDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {


    private final MovieService movieService;

    @GetMapping
    @ApiOperation(value = "Получить список фильмов")

    public ResponseEntity<List<MovieResponseDto>> getMovies() {
        try {
            return ResponseEntity.ok(movieService.getMovies());
        } catch (Exception e) {
            log.error("error to get movie list");
            return ResponseEntity.badRequest().build();
        }

    }


    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Получить фильм по id", response = MovieResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 400, message = "Некорректный запрос"),
            @ApiResponse(code = 404, message = "Фильм не найден"),
            @ApiResponse(code = 500, message = "Системная ошибка")

    })
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable Long id) {
        log.info("request film info by id:" + id);
        MovieResponseDto movie = movieService.getMovie(id);
        return ResponseEntity.ok(movie);


    }


    @ApiOperation(value = "Добавить фильм", response = MovieCreateRequestDto.class)
    @PostMapping
    public ResponseEntity<MovieResponseDto> addMovie(
            @ApiParam(value = "информация о фильме", required = true)
            @RequestBody MovieCreateRequestDto newMovie,
            @RequestHeader("user") String username) {
        MDC.put("user", username);
        log.warn("try to add new movie. data: " +newMovie.toString());

            newMovie.setUser(username);
            MovieResponseDto movie = movieService.addMovie(newMovie);
            return ResponseEntity.ok().body(movie);

    }
    @ApiOperation(value = "Обновить данные о фильме")
    @PatchMapping ("/{id}")
    public ResponseEntity<MovieResponseDto> patchMovie(@PathVariable Long id, @RequestBody MovieCreateRequestDto updatedMovie) {
        MovieResponseDto movie = movieService.patchMovie(id, updatedMovie);
        log.info("Movie updated. data:" + movie.toString());
        return ResponseEntity.ok().body(movie);
    }

    @ApiOperation(value = "Удалить фильм из списка по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {

        movieService.deleteMovie(id);
        log.info("movie deleted, id: "+ id );
        return ResponseEntity.ok().body("successful");
    }


    @ApiOperation(value = "Обновить данные о фильме")
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable Long id, @RequestBody MovieCreateRequestDto updatedMovie) {
        MovieResponseDto movie = movieService.updateMovie(id, updatedMovie);
        log.info("Movie updated. data:" + movie.toString());
        return ResponseEntity.ok().body(movie);

    }

}
