package beyondeyesight.chat.config;

import beyondeyesight.chat.domain.ChatMessage;
import beyondeyesight.chat.infra.RedisSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import static org.mockito.Mockito.mock;

@Import({LettuceConnectionFactory.class})
@TestConfiguration
public class TestRedisConfig {
    public static final String CHANNEL_NAME = "TEST_CHANNEL";

    @Bean
    public RedisMessageListenerContainer redisMessageListener(RedisConnectionFactory lettuceConnectionFactory, SimpMessageSendingOperations simpMessageSendingOperations) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory);
        container.addMessageListener(messageListenerAdapter(lettuceConnectionFactory, simpMessageSendingOperations), new ChannelTopic(CHANNEL_NAME));
        return container;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatMessage.class));
        return redisTemplate;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(RedisConnectionFactory lettuceConnectionFactory, SimpMessageSendingOperations simpMessageSendingOperations) {
        return new MessageListenerAdapter(new RedisSubscriber(redisTemplate(lettuceConnectionFactory), objectMapper(), simpMessageSendingOperations));
    }

    @Bean
    public SimpMessageSendingOperations simpMessageSendingOperations() {
        return mock(SimpMessageSendingOperations.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return mock(ObjectMapper.class);
    }
}
