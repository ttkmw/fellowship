package beyondeyesight.chat.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.chat.config.TestCassandraConfig;
import beyondeyesight.chat.domain.ChatMessage;
import beyondeyesight.chat.domain.ChatRoom;
import beyondeyesight.chat.domain.Sender;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = TestCassandraConfig.class)
@CassandraDataSet(keyspace = "testKeySpace", value = {"dataset.cql"})
@TestExecutionListeners(listeners = {
    CassandraUnitDependencyInjectionTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class}
)
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@EmbeddedCassandra(timeout = 60000)
public class ChatMessageRepositoryTest {

    private static final Log LOGGER = LogFactory.getLog(ChatMessageRepositoryTest.class);

    public static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS testKeySpace WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '3' };";

    public static final String KEYSPACE_ACTIVATE_QUERY = "USE testKeySpace;";

    public static final String DATA_TABLE_NAME = "book";

    @Autowired
    private ChatMessageRepository chatMessageRepository;

//    @Autowired
//    private CassandraAdminOperations adminTemplate;


    //todo: ID 생성을 DB 레벨에서 할 수 있는지 확인. 애플리케이션에서는 ID 안넣어도 DB에서 생성해주는지.
    @DisplayName("#save() : should not be null after saving entity")
    @Test
    void save() {
        ChatRoom chatRoom = ChatRoom.of("chatRoom");
        Sender sender = Sender.of(UUID.randomUUID());
        ChatMessage chatMessage = ChatMessage.of(chatRoom, sender,"haha");

        chatMessage = chatMessageRepository.save(chatMessage);
        assertThat(chatMessage).isNotNull();
        System.out.println("kkkk");
        System.out.println(chatMessage);
    }
//    @After
//    public void dropTable() {
//        adminTemplate.dropTable(CqlIdentifier.fromCql(DATA_TABLE_NAME));
//    }
//
//    @Before
//    public void createTable() throws InterruptedException, TTransportException, ConfigurationException, IOException {
//        adminTemplate.createTable(true, CqlIdentifier.fromCql(DATA_TABLE_NAME), ChatMessage.class, new HashMap<String, Object>());
//    }
//
//
//    @BeforeClass
//    public static void startCassandraEmbedded()
//        throws InterruptedException, TTransportException, ConfigurationException, IOException {
//        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
//        final Cluster cluster = Cluster.builder().addContactPoints("localhost").withPort(9042).build();
//        LOGGER.info("Server Started at 127.0.0.1:9142... ");
//        final Session session = cluster.connect();
//        session.execute(KEYSPACE_CREATION_QUERY);
//        session.execute(KEYSPACE_ACTIVATE_QUERY);
//        LOGGER.info("KeySpace created and activated.");
//        Thread.sleep(5000);
//    }
//
//    @AfterClass
//    public static void stopCassandraEmbedded() {
//        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
//    }
}
