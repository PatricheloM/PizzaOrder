package hu.pizzaorder.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hu.pizzaorder.backend.dto.AccountDTO;
import hu.pizzaorder.backend.dto.LoginDTO;
import hu.pizzaorder.backend.service.AccountService;
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
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AccountService accountService;

    @Autowired
    TokenService tokenService;

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Object> login(@RequestBody Object object) {
        try {
            if (JsonValidator.validate(JsonFactory.produce(object), LoginDTO.class)) {
                LoginDTO login = modelMapper.map(object, LoginDTO.class);
                Optional<AccountDTO> account = accountService.getAccountByUsername(login.getUsername().toLowerCase());
                if (account.isPresent() && account.get().getPassword().equals(login.getPassword())) {
                    return new ResponseEntity<>(tokenService.generateToken(login.getUsername()), HttpStatus.OK);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
            }
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (SchemaNotFoundException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Object> register(@RequestBody Object object) {
        try {
            if (JsonValidator.validate(JsonFactory.produce(object), AccountDTO.class)) {
                AccountDTO form = modelMapper.map(object, AccountDTO.class);
                Optional<AccountDTO> account = accountService.getAccountByUsername(form.getUsername().toLowerCase());
                if(account.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                } else {
                    for (AccountDTO acc : accountService.getAllAccounts()) {
                        if (acc.getEmail().equals(form.getEmail())) {
                            return ResponseEntity.status(HttpStatus.CONFLICT).build();
                        }
                    }
                }
                accountService.register(form);
                return ResponseEntity.ok().build();
            }
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (SchemaNotFoundException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "auth/{token}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Object> auth(@PathVariable("token")String token) {
        Optional<AccountDTO> account = accountService.getAccountByUsername(tokenService.getUserByToken(token).toLowerCase());
        if (tokenService.tokenExists(token) && account.isPresent()) {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
