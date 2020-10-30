package beyondeyesight.chat.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.chat.config.TestCassandraConfig;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ActiveProfiles("test")
@TestExecutionListeners(listeners = {
    CassandraUnitDependencyInjectionTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class}
)
@EmbeddedCassandra(timeout = 60000)
@SpringBootTest(classes = TestCassandraConfig.class)
class ChatMessageServiceTest {

    @Autowired
    private ChatMessageService chatMessageService;

    @Test
    void di() {
        assertThat(chatMessageService).isNotNull();
    }
}
