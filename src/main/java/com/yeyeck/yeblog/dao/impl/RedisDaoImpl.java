package com.yeyeck.yeblog.dao.impl;

import com.yeyeck.yeblog.dao.IRedisDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisDaoImpl implements IRedisDao {

    private RedisTemplate<String, Object> redisTemplate;

    private StringRedisTemplate stringRedisTemplate;

    public RedisDaoImpl(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
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
    public void addString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void addString(String key, String value, long expireTime, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, expireTime, unit);
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Long hyperLogLogSize(String key) {
        return stringRedisTemplate.opsForHyperLogLog().size(key);
    }

    @Override
    public long hyperLogLogAdd(String key, String... values) {
        return stringRedisTemplate.opsForHyperLogLog().add(key, values);
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
