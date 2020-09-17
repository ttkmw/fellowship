package beyondeyesight.chat.config;

import beyondeyesight.chat.domain.ChatMessage;
import beyondeyesight.chat.infra.RedisSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Configuration
public class RedisConfig {

    @Bean
    public RedisMessageListenerContainer redisMessageListener(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper, SimpMessageSendingOperations simpMessageSendingOperations) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter(connectionFactory, objectMapper, simpMessageSendingOperations), new ChannelTopic("TEST_CHANNEL"));
//        container.addMessageListener(messageListenerAdapter(connectionFactory, objectMapper, simpMessageSendingOperations), new ChannelTopic("channel2"));
        return container;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatMessage.class));
        return redisTemplate;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper, SimpMessageSendingOperations simpMessageSendingOperations) {
        return new MessageListenerAdapter(new RedisSubscriber(redisTemplate(connectionFactory), objectMapper, simpMessageSendingOperations));
    }
}
