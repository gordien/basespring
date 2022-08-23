package ru.gordy.gascrudapi.dto;

import lombok.Data;

@Data
public class MovieResponseDto {
    private Long id;
    private String title;
    private Boolean viewed;
    private Integer year;
    private String user;
}
