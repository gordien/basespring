package ru.gordy.gascrudapi.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.gordy.gascrudapi.annotation.Benchmark;
import ru.gordy.gascrudapi.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieRepo extends CrudRepository<MovieEntity, Long> {
    @Benchmark
    MovieEntity findByTitleAndYear(String title, Integer year);


    List<MovieEntity> findAll();

    Optional<MovieEntity> findById(Long id);
//    boolean existsById(Long id);

    void deleteById(Long id);


}
