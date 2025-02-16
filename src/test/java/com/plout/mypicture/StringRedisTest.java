package com.plout.mypicture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StringRedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisStringOperation() {
        //获取操作对象
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();

        //key 和 value
        String key = "testKey";
        String value = "testValue";

        //测试新增或更新操作
        valueOps.set(key, value);
        String storeValue = valueOps.get(key);
        assertEquals(value, storeValue, "存储和获取的值不相等");
    }
}
