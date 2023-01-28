package hu.pizzaorder.backend.service;

import hu.pizzaorder.backend.dto.PizzaDTO;
import hu.pizzaorder.backend.model.Pizza;
import hu.pizzaorder.backend.repository.PizzaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<PizzaDTO> allPizzas() {
        return pizzaRepository.findAll().stream().map(pizza -> modelMapper.map(pizza, PizzaDTO.class)).collect(Collectors.toList());
    }

    public Optional<PizzaDTO> findPizza(String name) {
        Optional<Pizza> pizza = pizzaRepository.findByName(name);

        return pizza.map(value -> modelMapper.map(value, PizzaDTO.class));
    }

    public boolean pizzaExists(Long id) {
        return pizzaRepository.existsById(id);
    }
}
