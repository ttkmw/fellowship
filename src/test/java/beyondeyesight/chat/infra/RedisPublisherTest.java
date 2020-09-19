package beyondeyesight.chat.infra;

import beyondeyesight.chat.domain.ChatMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class RedisPublisherTest {
    @Mock
    private RedisTemplate<String, ChatMessage> mockRedisTemplate;

    @Test
    void publish() {
        //given
        RedisPublisher redisPublisher = new RedisPublisher(mockRedisTemplate);
        ChannelTopic mockChannelTopic = mock(ChannelTopic.class);
        ChatMessage mockChatMessage = mock(ChatMessage.class);
        //when
        redisPublisher.publish(mockChannelTopic, mockChatMessage);
        //then
        verify(mockRedisTemplate).convertAndSend(mockChannelTopic.getTopic(), mockChatMessage);
    }
}
