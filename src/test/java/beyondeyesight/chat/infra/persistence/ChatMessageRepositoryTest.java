package beyondeyesight.chat.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.chat.config.TestCassandraConfig;
import beyondeyesight.chat.domain.ChatMessage;
import beyondeyesight.chat.domain.ChatRoom;
import beyondeyesight.chat.domain.Sender;
import java.util.UUID;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


/*
 * reference: https://github.com/omlip/cassandra-unit-spring-demo
 * */
@SpringBootTest(classes = TestCassandraConfig.class)
@ActiveProfiles("test")
@TestExecutionListeners(listeners = {
    CassandraUnitDependencyInjectionTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class}
)
@EmbeddedCassandra(timeout = 60000)
public class ChatMessageRepositoryTest {

    @Autowired
    private ChatMessageRepository chatMessageRepository;


    //todo: ID 생성을 DB 레벨에서 할 수 있는지 확인. 애플리케이션에서는 ID 안넣어도 DB에서 생성해주는지.
    @DisplayName("#save() : should not be null after saving entity")
    @Test
    void save() {
        ChatRoom chatRoom = ChatRoom.of("chatRoom");
        Sender sender = Sender.of(UUID.randomUUID());
        ChatMessage chatMessage = ChatMessage.of(chatRoom, sender, "chatBody");

        chatMessage = chatMessageRepository.save(chatMessage);
        assertThat(chatMessage).isNotNull();
    }
}
