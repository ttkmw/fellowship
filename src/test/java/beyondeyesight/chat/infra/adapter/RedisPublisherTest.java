package beyondeyesight.chat.infra.adapter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import beyondeyesight.chat.domain.model.ChatMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class RedisPublisherTest {
    @Mock
    private RedisTemplate<String, ChatMessage> mockRedisTemplate;

    @Test
    void publish() {
        //given
        RedisPublisher redisPublisher = new RedisPublisher(mockRedisTemplate);
        ChatMessage mockChatMessage = mock(ChatMessage.class);
        //when
        redisPublisher.publish(mockChatMessage);
        //then
        verify(mockRedisTemplate).convertAndSend(mockChatMessage.getChatRoomId(), mockChatMessage);
    }
}
