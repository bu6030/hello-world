package com.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 封装好的Redis工具，可以直接使用
 */
@Repository
public class RedisRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    public void addCount(String key) {
        redisTemplate.opsForValue().increment(key, 1);
    }

    public int getCount(String key) {
        Object object = redisTemplate.opsForValue().increment(key, 0);
        return object == null ? 0 : Integer.parseInt(String.valueOf(object));
    }

    public boolean removeCount(String key) {
        return redisTemplate.delete(key);
    }

    public boolean isFirstTimeSend(String key, int value, long releaseTime) {
        Boolean boo = redisTemplate.opsForValue().setIfAbsent(key, value, releaseTime, TimeUnit.SECONDS);
        return boo != null && boo;

    }

    public void cache(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public void expire(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    public Object get(String key) {
        return (Object) redisTemplate.opsForValue().get(key);
    }

    public Object remove(String key) {
        return redisTemplate.delete(key);
    }

    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    public void leftPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public void addSet(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public boolean isMember(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public List<Object> multiGet(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptyList();
        }
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public String rightPop(String key) {
        return (String) redisTemplate.opsForList().rightPop(key);
    }

    public long size(String key) {
        if (!redisTemplate.hasKey(key)) {
            return 0;
        }
        return redisTemplate.opsForList().size(key);
    }

    public Set<String> getKeys(String keyPattern) {
        return redisTemplate.keys(keyPattern+ "*");
    }

    public Long deleteKeys(Set<String> keys) {
        return redisTemplate.delete(keys);
    }
}
