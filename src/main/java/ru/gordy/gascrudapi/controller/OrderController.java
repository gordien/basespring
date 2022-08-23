package ru.gordy.gascrudapi.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gordy.gascrudapi.dto.OrderCreateRequestDto;
import ru.gordy.gascrudapi.dto.OrderPhoneResultDto;
import ru.gordy.gascrudapi.dto.OrderResponseDto;
import ru.gordy.gascrudapi.service.OrderService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> addOrder(
            @ApiParam(value = "order info", required = true)
            @RequestBody OrderCreateRequestDto newOrder) {
            OrderResponseDto order = orderService.addOrder(newOrder);
            return ResponseEntity.ok().body(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<List<OrderPhoneResultDto>> getOrdersById (@PathVariable int orderId) {
        System.out.println("orderId");
        System.out.println(orderId);
        return ResponseEntity.ok().body(orderService.getOrdersById(orderId));
    }






}
