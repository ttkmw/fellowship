package beyondeyesight.chat.config;

import beyondeyesight.chat.domain.ChatMessage;
import beyondeyesight.chat.infra.RedisSubscriber;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
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
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        Jackson2JsonRedisSerializer<String> serializer = new Jackson2JsonRedisSerializer<>(
            String.class);
        serializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
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
