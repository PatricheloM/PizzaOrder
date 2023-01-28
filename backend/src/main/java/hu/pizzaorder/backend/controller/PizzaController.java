package hu.pizzaorder.backend.controller;

import hu.pizzaorder.backend.dto.PizzaDTO;
import hu.pizzaorder.backend.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Object> pizzas() {
        return new ResponseEntity<>(pizzaService.allPizzas(), HttpStatus.OK);
    }

    @GetMapping(value = "/{pizza}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Object> pizzas(@PathVariable("pizza")String pizza) {
        Optional<PizzaDTO> result = pizzaService.findPizza(pizza);
        return result.<ResponseEntity<Object>>map(pizzaDTO -> new ResponseEntity<>(pizzaDTO, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
     }
}
