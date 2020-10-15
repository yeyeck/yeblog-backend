package com.yeyeck.yeblog.dao;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface IRedisDao {


    <T>T get(String key);

    void add(String key, Object value);

    void add(String key, Object value, long expireTime, TimeUnit unit);

    long addToList(String key, Object... values);

    <T> List<T> getList(String key);

    void addString(String key, String value);

    void addString(String key, String value, long expireTime, TimeUnit unit);

    String getString(String key);

    Long hyperLogLogSize(String key);

    long hyperLogLogAdd(String key, String... values);

    boolean delete(String key);

    Set<String> keys(String pattern);

    boolean hasKey(String key);

}
