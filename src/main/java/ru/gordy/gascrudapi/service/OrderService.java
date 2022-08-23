package ru.gordy.gascrudapi.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.gordy.gascrudapi.dto.OrderCreateRequestDto;
import ru.gordy.gascrudapi.dto.OrderPhoneResultDto;
import ru.gordy.gascrudapi.dto.OrderResponseDto;
import ru.gordy.gascrudapi.mapper.OrderMapper;
import ru.gordy.gascrudapi.dao.OrderRepo;
import ru.gordy.gascrudapi.entity.OrderEntity;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepo orderRepo;
    OrderMapper orderMapper;

    public OrderResponseDto addOrder(OrderCreateRequestDto order) {
        OrderEntity newOrder = orderMapper.mapOrderRequestToEntity(order);
        OrderEntity addedOrder = orderRepo.save(newOrder);
        OrderResponseDto addedOrderResponse = orderMapper.mapOrderEntityToResponse(addedOrder);
        return addedOrderResponse;
    }

    public List<OrderResponseDto> getOrders() {
        return orderRepo.findAll().stream().map(orderMapper::mapOrderEntityToResponse).collect(Collectors.toList());
    }


    public List<OrderPhoneResultDto> getOrdersById(int orderId) {
        Map<String, OrderPhoneResultDto> agrregationResult = new HashMap<>();
        List<OrderEntity> sourceRows = orderRepo.findAllByOrderId(orderId);
        sourceRows.forEach(
                order -> agrregationResult.merge(order.getPhone(), new OrderPhoneResultDto(
                        order.getPhone(),
                        order.getOrderAmount()
                ), (current, newest) -> {
                    current.setTotalAmount(current.getTotalAmount() + newest.getTotalAmount());
                    return current;
                })
        );
        List<OrderPhoneResultDto> resultList = agrregationResult.values().stream().sorted(Comparator.comparing(OrderPhoneResultDto::getPhone)).collect(Collectors.toList());

        return resultList;
    }
}
