package beyondeyesight.chat.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisConfig.class, LettuceConnectionFactory.class})
class RedisConfigTest {

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    @Test
//    void redisMessageListener() {
//        assertThat(redisMessageListenerContainer).isNotNull();
//    }

//    @Test
//    void redisTemplate() {
//        assertThat(redisTemplate).isNotNull();
//    }
}