package beyondeyesight.chat.infra.adapter;

import beyondeyesight.chat.domain.adapter.MessagePublisher;
import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.model.ChatRoom;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher implements MessagePublisher {
    private final RedisTemplate<String, ChatMessage> redisTemplate;

    public RedisPublisher(RedisTemplate<String, ChatMessage> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(ChatRoom chatRoom, ChatMessage chatMessage) {
        redisTemplate.convertAndSend(chatRoom.getName(), chatMessage);
    }
}
