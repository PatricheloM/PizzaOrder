package hu.pizzaorder.backend.service;

import hu.pizzaorder.backend.dto.TokenDTO;
import hu.pizzaorder.backend.repository.RedisRepository;
import hu.pizzaorder.backend.util.TokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    RedisRepository redisRepository;

     public TokenDTO generateToken(String username) {
         String token = TokenFactory.generate();
         redisRepository.setex(token, username, 86400);
         return new TokenDTO(token);
     }

     public boolean tokenExists(String token) {
         return redisRepository.exists(token);
     }

     public String getUserByToken(String token) {
         return redisRepository.get(token);
     }
}
