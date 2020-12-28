package beyondeyesight.chat.config;

import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.infra.adapter.RedisSubscriber;
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
        //todo: refac CassandraTemplate을 활용하면 쉽게 addMessageListner 할 수 있을듯.
        container.addMessageListener(messageListenerAdapter(connectionFactory, objectMapper, simpMessageSendingOperations), new ChannelTopic("110841e3-e6fb-4191-8fd8-5674a5107c33"));
        container.addMessageListener(messageListenerAdapter(connectionFactory, objectMapper, simpMessageSendingOperations), new ChannelTopic("4f0a4a02-26c6-4441-915d-c0f61cda0178"));
        container.addMessageListener(messageListenerAdapter(connectionFactory, objectMapper, simpMessageSendingOperations), new ChannelTopic("cd865f7d-3923-4065-a5e6-5c093e7b5442"));
        container.addMessageListener(messageListenerAdapter(connectionFactory, objectMapper, simpMessageSendingOperations), new ChannelTopic("56645394-80e0-4fb3-a2ad-097832e39233"));
        return container;
    }

    @Bean
    public RedisTemplate<String, ChatMessage> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ChatMessage> redisTemplate = new RedisTemplate<>();
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
