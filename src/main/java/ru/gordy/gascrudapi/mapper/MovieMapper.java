package ru.gordy.gascrudapi.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ru.gordy.gascrudapi.dto.MovieCreateRequestDto;
import ru.gordy.gascrudapi.dto.MovieResponseDto;
import ru.gordy.gascrudapi.entity.MovieEntity;


@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mappings({
            @Mapping(target = "author", source = "user")
    })
    MovieEntity mapRequestToMovieEntity(MovieCreateRequestDto createRequestDto);

    @Mappings({
            @Mapping(target = "user", source = "author")
    })
    MovieResponseDto mapToMovieResponseDto(MovieEntity movieEntity);


}
