package hu.pizzaorder.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hu.pizzaorder.backend.dto.AccountDTO;
import hu.pizzaorder.backend.dto.OrdersDTO;
import hu.pizzaorder.backend.service.AccountService;
import hu.pizzaorder.backend.service.OrdersService;
import hu.pizzaorder.backend.service.PizzaService;
import hu.pizzaorder.backend.service.TokenService;
import hu.pizzaorder.backend.util.ex.SchemaNotFoundException;
import hu.pizzaorder.backend.util.json.JsonFactory;
import hu.pizzaorder.backend.util.validator.JsonValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrdersService ordersService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AccountService accountService;

    @Autowired
    PizzaService pizzaService;

    @PostMapping(value = "/order", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Object> order(@RequestBody Object object, @RequestParam String token) {
        if (tokenService.tokenExists(token)) {
            String tokenValue = tokenService.getUserByToken(token).toLowerCase();
            Optional<AccountDTO> account = accountService.getAccountByUsername(tokenValue);
            if (account.isPresent()) {
                try {
                    if (JsonValidator.validate(JsonFactory.produce(object), OrdersDTO.class)) {
                        OrdersDTO ordersDTO = modelMapper.map(object, OrdersDTO.class);
                        if (ordersDTO.getUsername().equals(tokenValue)) {
                            for (Long pId : ordersDTO.getPizzaIds()) {
                                if (!pizzaService.pizzaExists(pId)) {
                                    return ResponseEntity.notFound().build();
                                }
                            }
                            ordersService.order(ordersDTO);
                            return ResponseEntity.ok().build();
                        } else {
                            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                        }
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
                }
                catch (SchemaNotFoundException | JsonProcessingException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping(value = "/{token}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getUsersOrders(@PathVariable("token")String token) {
        if (tokenService.tokenExists(token)) {
            return new ResponseEntity<>(ordersService.findByUsername(tokenService.getUserByToken(token)), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
