package ru.gordy.gascrudapi.mapper;

import org.mapstruct.Mapper;
import ru.gordy.gascrudapi.dto.OrderCreateRequestDto;
import ru.gordy.gascrudapi.dto.OrderResponseDto;
import ru.gordy.gascrudapi.entity.OrderEntity;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    public OrderEntity mapOrderRequestToEntity(OrderCreateRequestDto order);

    public OrderResponseDto mapOrderEntityToResponse(OrderEntity order);

}
