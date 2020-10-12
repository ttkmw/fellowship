package beyondeyesight.chat.config;

import java.util.Collections;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.lang.NonNull;

@TestConfiguration
public class TestCassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.local-datacenter}")
    private String localDatacenter;

    @Override
    @Bean("testCassandraSession")
    @Primary
    @Nonnull
    public CqlSessionFactoryBean cassandraSession() {
//        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        cqlSessionFactoryBean.setContactPoints("localhost");
        cqlSessionFactoryBean.setPort(port);
        cqlSessionFactoryBean.setKeyspaceName("testKeySpace");
        cqlSessionFactoryBean.setLocalDatacenter(localDatacenter);

        //todo: 걍 CreateKeyspaceSpecification.createKeyspace 만 해도 될듯?
        cqlSessionFactoryBean.setKeyspaceCreations(
            Collections.singletonList(
                CreateKeyspaceSpecification.createKeyspace("testKeySpace")
                    .ifNotExists()
                    .withSimpleReplication(1)
            )
        );
        return cqlSessionFactoryBean;
    }

//    @Bean
//    public CassandraClusterFactoryBean cluster() {
//        try {
//            EmbeddedCassandraServerHelper.startEmbeddedCassandra(EmbeddedCassandraServerHelper.DEFAULT_CASSANDRA_YML_FILE, 1000000L);
//            EmbeddedCassandraServerHelper.getCluster().getConfiguration().getSocketOptions().setReadTimeoutMillis(1000000);
//            Session session = EmbeddedCassandraServerHelper.getSession();
//            CQLDataLoader dataLoader = new CQLDataLoader(session);
//            dataLoader.load(new FileCQLDataSet("schema.ddl", false, false, getKeyspaceName()));
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            throw new RuntimeException("Can't start Embedded Cassandra", e);
//        }
//        return super.cluster();
//    }
//    @Override
//    protected String getKeyspaceName() {
//        return keyspace;
//    }
//    @Override
//    protected int getPort() {
//        return 9142;
//    }
//}

    @Override
    @Nonnull
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @NonNull
    @Override
    protected String getKeyspaceName() {
        return "testKeySpace";
    }

    @Override
    @Nonnull
    public String[] getEntityBasePackages() {
        return new String[] {"beyondeyesight.chat.domain"};
    }
}
