package beyondeyesight.chat.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.chat.config.TestCassandraConfig;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ActiveProfiles("test")
@TestExecutionListeners(listeners = {
    CassandraUnitDependencyInjectionTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class}
)
@EmbeddedCassandra(timeout = 60000)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestCassandraConfig.class}, loader = SpringBootContextLoader.class)
@CassandraDataSet(keyspace = "testKeySpace", value = {"db/cql/init_chatRooms.cql"})
public class ChatRoomCassandraRepositoryTest {

    @Autowired
    private ChatRoomCassandraRepository chatRoomCassandraRepository;

    @Test
    public void findAll() {
        assertThat(chatRoomCassandraRepository.findAll()).isNotEmpty();
    }

}
