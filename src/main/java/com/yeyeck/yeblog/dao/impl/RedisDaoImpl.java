package com.yeyeck.yeblog.dao.impl;

import com.yeyeck.yeblog.dao.IRedisDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisDaoImpl implements IRedisDao {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisDaoImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <T> T get(String key) {
        return (T)redisTemplate.opsForValue().get(key);
    }

    @Override
    public void add(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void add(String key, Object value, long expireTime, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, expireTime, unit);
    }

    @Override
    public long addToList(String key, Object... values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }
    @Override
    public <T> List<T> getList(String key) {
        return (List<T>)redisTemplate.opsForList().range(key, 0, -1);
    }


    @Override
    public Long hyperLogLogSize(String key) {
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    @Override
    public long hyperLogLogAdd(String key, String... values) {
        return redisTemplate.opsForHyperLogLog().add(key, values);
    }

    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
