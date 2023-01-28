package hu.pizzaorder.backend.repository;

import hu.pizzaorder.backend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUsername(String username);
}
