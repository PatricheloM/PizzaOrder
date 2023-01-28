package hu.pizzaorder.backend.service;

import hu.pizzaorder.backend.dto.OrdersDTO;
import hu.pizzaorder.backend.model.Orders;
import hu.pizzaorder.backend.repository.OrdersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ModelMapper modelMapper;

    public void order(OrdersDTO ordersDTO) {
        ordersRepository.save(modelMapper.map(ordersDTO, Orders.class));
    }

    public List<OrdersDTO> findByUsername(String username) {
        return ordersRepository.findByUsername(username).stream().map(orders -> modelMapper.map(orders, OrdersDTO.class)).collect(Collectors.toList());
    }
}
