package com.kdt.goohae.config;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisRepositoryConfigTest {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void 레디스_저장_테스트(){
        //given
        ValueOperations<String, Object> sovo = redisTemplate.opsForValue();
        sovo.set("user_id", "dbdb1114");


        //when
        String findId = (String) redisTemplate.opsForValue().get("user_id");
        System.out.println(findId);

        //then
        Assertions.assertThat(findId).isEqualTo("dbdb1114");
    }

    @Test
    void 레디스_삭제_테스트() {
        //given
        ValueOperations<String, Object> sovo = redisTemplate.opsForValue();

        sovo.set("user_Id", "dbdb1114");
        sovo.set("ValueOperations", "ValueOperations");
        sovo.set("redisTemplate", "redisTemplate");

        //when
        sovo.getAndDelete("ValueOperations");
        redisTemplate.delete("redisTemplate");

        //then
        assertThat(sovo.get("ValueOperations")).isNull();
        assertThat(sovo.get("redisTemplate")).isNull();
    }

}
