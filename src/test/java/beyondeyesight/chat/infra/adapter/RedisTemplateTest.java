package beyondeyesight.chat.infra.adapter;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import beyondeyesight.chat.config.EmbeddedRedisConfig;
import beyondeyesight.chat.config.TestRedisConfig;
import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.model.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {EmbeddedRedisConfig.class, TestRedisConfig.class})
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, ChatMessage> redisTemplate;

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @Autowired
    private ObjectMapper objectMapper;

    /*
     * 이 테스트는 restTemplate이 message를 send할 시, RedisSubscriber가 message를 수신하는 것까지 확인한다.
     * */
    @Test
    public void pubsub() throws InterruptedException, JsonProcessingException {
        UUID chatRoomId = UUID.randomUUID();
        Sender sender = Sender.of(UUID.randomUUID());
        ChatMessage chatMessage = ChatMessage.of(chatRoomId, sender, "testBody");
        when(objectMapper.readValue(anyString(), eq(ChatMessage.class))).thenReturn(chatMessage);
        redisTemplate.convertAndSend(TestRedisConfig.CHANNEL_NAME, chatMessage);
        Thread.sleep(50);
        verify(simpMessageSendingOperations)
            .convertAndSend("/sub/chat/room/" + "chatRoom", chatMessage);
    }
}
