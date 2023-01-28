package hu.pizzaorder.backend.config;

import hu.pizzaorder.backend.model.RedisConfig;
import hu.pizzaorder.backend.util.ex.ConfigNotFoundException;
import hu.pizzaorder.backend.util.json.ObjectFactory;
import org.modelmapper.*;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableCaching
public class AppConfig {

    @Bean
    JedisPool jedisPool() {
        RedisConfig redisConfig;
        try {
            redisConfig = ObjectFactory.produce(AppConfig.class.getResourceAsStream("redis.json"), RedisConfig.class);
        } catch (Exception e) {
            throw new ConfigNotFoundException(e);
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxPool());
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdlePool());
        jedisPoolConfig.setMinIdle(redisConfig.getMinIdlePool());
        return new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout(), redisConfig.getPassword());
    }

    Provider<Date> dateProvider = new AbstractProvider<Date>() {
        @Override
        public Date get() {
            return new Date();
        }
    };

    Converter<String, Date> toStringDate = new AbstractConverter<String, Date>() {
        @Override
        protected Date convert(String source) {
            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            try {
                return sdt.parse(source);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    };

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(String.class, Date.class);
        modelMapper.addConverter(toStringDate);
        modelMapper.getTypeMap(String.class, Date.class).setProvider(dateProvider);
        return modelMapper;
    }
}
