package xyz.devcham.farmap.utils;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private final StringRedisTemplate redis;

    public CacheService(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public void put(String key, String value) {
        redis.opsForValue().set(key, value);
    }

    public void put(String key, String value, Duration duration) {
        redis.opsForValue().set(key, value, duration);
    }

    public String get(String key) {
        return redis.opsForValue().get(key);
    }
}
