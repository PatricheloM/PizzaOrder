package hu.pizzaorder.backend.repository;

import hu.pizzaorder.backend.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Optional<Pizza> findByName(String name);
}
