package ru.gordy.gascrudapi.dto;

import lombok.Data;

@Data
public class OrderPhoneResultDto {
    public String phone;
    public Long totalAmount;


    public OrderPhoneResultDto(String phone, Long totalAmount) {
        this.phone = phone;
        this.totalAmount = totalAmount;
    }

    public OrderPhoneResultDto() {
    }
}
