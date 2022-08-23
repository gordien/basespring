package ru.gordy.gascrudapi.dto;

import lombok.Data;

@Data
public class MovieDto {

    private Long id;
    private String title;
    private Boolean viewed;
    private Integer year;


}
