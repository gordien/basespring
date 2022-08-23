package ru.gordy.gascrudapi.dto;

import lombok.Data;

@Data
public class OrderCreateRequestDto {

    public int orderId;
    public String phone;
    public Long orderAmount;

    public OrderCreateRequestDto(int orderId, String phone, Long orderAmount) {
        this.orderId = orderId;
        this.phone = phone;
        this.orderAmount = orderAmount;
    }
}
