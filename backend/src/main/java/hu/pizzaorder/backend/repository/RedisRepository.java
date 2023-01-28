package hu.pizzaorder.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RedisRepository {

    @Autowired
    JedisPool jedisPool;

    public void setex(String key, String value, int expiration) {
        Jedis jedis = jedisPool.getResource();
        jedis.setex(key, expiration, value);
        jedis.close();
    }

    public boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        boolean response = jedis.exists(key);
        jedis.close();
        return response;
    }

    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String response = jedis.get(key);
        jedis.close();
        return response;
    }

    public List<String> keys(String regex) {
        Jedis jedis = jedisPool.getResource();
        List<String> response = new ArrayList<>(jedis.keys(regex));
        jedis.close();
        return response;
    }
}
