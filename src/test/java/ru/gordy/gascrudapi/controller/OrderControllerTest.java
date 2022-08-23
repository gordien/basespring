package ru.gordy.gascrudapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import ru.gordy.gascrudapi.dto.OrderCreateRequestDto;
import ru.gordy.gascrudapi.dto.OrderPhoneResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
public class OrderControllerTest {

    @Autowired
    OrderController orderController;


    @BeforeEach
    void init() {
        OrderCreateRequestDto order1 = new OrderCreateRequestDto(
                1000,
                "+79261111111",
                100L
        );
        OrderCreateRequestDto order2 = new OrderCreateRequestDto(
                1000,
                "+79261111111",
                200L
        );
        OrderCreateRequestDto order3 = new OrderCreateRequestDto(
                1000,
                "+79262222222",
                1000L
        );
        OrderCreateRequestDto order4 = new OrderCreateRequestDto(
                2000,
                "+79263333333",
                5000L
        );

        List<OrderCreateRequestDto> orderList = Arrays.asList(order1, order2, order3, order4);

        for (OrderCreateRequestDto requestOrder : orderList) {
            orderController.addOrder(requestOrder);
        }


    }

    private ResponseEntity<List<OrderPhoneResultDto>> getResponseFromJson(String responseJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<OrderPhoneResultDto> result = mapper.readValue(responseJson, new TypeReference<List<OrderPhoneResultDto>>(){});
        result.sort(Comparator.comparing(OrderPhoneResultDto::getPhone));
        return ResponseEntity.ok().body(result);
    }

    @Test
    void testAggreagtionByPhone()throws IOException {
        String correctResponse = " [{\"phone\": \"+79261111111\",\"totalAmount\": 300},{\"phone\": \"+79262222222\",\"totalAmount\": 1000}]";
        int orderId = 1000;
        ResponseEntity<List<OrderPhoneResultDto>> expectedResponse = getResponseFromJson(correctResponse);
        ResponseEntity<List<OrderPhoneResultDto>> requestResponse = orderController.getOrdersById(orderId);
        Assertions.assertEquals(expectedResponse,requestResponse);
    }


}
