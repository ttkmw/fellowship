package beyondeyesight.chat.infra.persistence;

import beyondeyesight.chat.config.TestCassandraConfig;
import beyondeyesight.chat.domain.ChatMessage;
import beyondeyesight.chat.domain.ChatRoom;
import beyondeyesight.chat.domain.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(classes = TestCassandraConfig.class)
@SpringBootTest(classes = TestCassandraConfig.class)
class ChatMessageRepositoryTest {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Test
    void haha() {
        ChatRoom chatRoom = ChatRoom.of("chatRoom");
        Sender sender = Sender.of("sender");
        ChatMessage chatMessage = ChatMessage.of("haha", "haha","haha");

        chatMessage = chatMessageRepository.save(chatMessage);
        System.out.println(chatMessage);
    }

}
