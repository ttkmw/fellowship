package beyondeyesight.chat.infra;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import beyondeyesight.chat.config.EmbeddedRedisConfig;
import beyondeyesight.chat.config.TestRedisConfig;
import beyondeyesight.chat.domain.ChatMessage;
import beyondeyesight.chat.domain.ChatRoom;
import beyondeyesight.chat.domain.RegionalChatRoom;
import beyondeyesight.chat.domain.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
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

    @Test
    public void pubsub() throws InterruptedException, JsonProcessingException {
        ChatRoom mockChatRoom = RegionalChatRoom.of("testRoomName", "testRegionalId");
        Sender mockSender = Sender.of("testId");
        ChatMessage chatMessage = ChatMessage.of(mockChatRoom, mockSender, "testBody");
        when(objectMapper.readValue(anyString(), eq(ChatMessage.class))).thenReturn(chatMessage);
        redisTemplate.convertAndSend(TestRedisConfig.CHANNEL_NAME, chatMessage);
        Thread.sleep(50);
        verify(simpMessageSendingOperations).convertAndSend("/sub/chat/room/" + "chatRoom", chatMessage);
    }
}
