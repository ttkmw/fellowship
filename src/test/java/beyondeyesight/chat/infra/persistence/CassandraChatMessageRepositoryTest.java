package beyondeyesight.chat.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.chat.config.TestCassandraConfig;
import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.model.Sender;
import java.util.UUID;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


/*
 * reference: https://github.com/omlip/cassandra-unit-spring-demo
 * */
@ActiveProfiles("test")
@TestExecutionListeners(listeners = {
    CassandraUnitDependencyInjectionTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class}
)
@EmbeddedCassandra(timeout = 60000)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestCassandraConfig.class}, loader = SpringBootContextLoader.class)
public class CassandraChatMessageRepositoryTest {

    @Autowired
    private CassandraChatMessageRepository cassandraChatMessageRepository;


    //todo: ID 생성을 DB 레벨에서 할 수 있는지 확인. 애플리케이션에서는 ID 안넣어도 DB에서 생성해주는지.
    @DisplayName("#save() : should not be null after saving entity")
    @Test
    public void save() {
        UUID id = UUID.randomUUID();
        UUID chatRoomId = UUID.randomUUID();
        Sender sender = Sender.of(UUID.randomUUID());
        ChatMessage chatMessage = ChatMessage.of(id, chatRoomId, sender, "chatBody");

        chatMessage = cassandraChatMessageRepository.save(chatMessage);
        assertThat(chatMessage).isNotNull();
    }
}
