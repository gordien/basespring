package ru.gordy.gascrudapi.dto;

import lombok.Data;

@Data
public class MovieCreateRequestDto {
    private String title;
    private Integer year;
    private Boolean viewed;
    private String user;
}
