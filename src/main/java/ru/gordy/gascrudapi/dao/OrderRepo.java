package ru.gordy.gascrudapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.gordy.gascrudapi.entity.OrderEntity;

import java.util.List;

@Component
public interface OrderRepo extends CrudRepository<OrderEntity,Long> {

    List<OrderEntity> findAll();

    List<OrderEntity> findAllByOrderId(int id);

    }
