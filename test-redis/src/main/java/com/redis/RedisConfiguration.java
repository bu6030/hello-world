package com.redis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@ConditionalOnClass(RedisTemplate.class)
public class RedisConfiguration {

    private String keyPrefix = "test-redis";

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(keySerializer());
        template.setValueSerializer(jsonRedisSerializer());
        return template;
    }

    @Bean
    public GenericJackson2JsonRedisSerializer jsonRedisSerializer() {
        ObjectMapper mapper = getObjectMapper4Redis();
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);
        GenericJackson2JsonRedisSerializer.registerNullValueSerializer(mapper, null);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        return serializer;
    }

    private ObjectMapper getObjectMapper4Redis() {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build();
    }

    public RedisSerializer<String> keySerializer() {
        return new RedisSerializer<String>() {

            @Override
            public byte[] serialize(String key) throws SerializationException {
                return RedisSerializer.string().serialize(String.join(":", keyPrefix, key));
            }

            @Override
            public String deserialize(byte[] bytes) throws SerializationException {
                String redisKey = RedisSerializer.string().deserialize(bytes);
                String[] tempValues = redisKey.split(":", 2);
                if (tempValues.length == 2) {
                    return tempValues[1];
                }
                return redisKey;
            }
        };
    }

}
