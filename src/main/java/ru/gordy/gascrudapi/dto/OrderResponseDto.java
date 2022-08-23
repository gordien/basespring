package ru.gordy.gascrudapi.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    public Long id;
    public int orderId;
    public String phone;
    public Long orderAmount;
}




