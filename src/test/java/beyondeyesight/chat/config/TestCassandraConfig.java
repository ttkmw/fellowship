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
    public CqlSessionFactoryBean cassandraSession() { ;
        CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        cqlSessionFactoryBean.setContactPoints(contactPoints);
        cqlSessionFactoryBean.setPort(port);
        cqlSessionFactoryBean.setKeyspaceName(keyspaceName);
        cqlSessionFactoryBean.setLocalDatacenter(localDatacenter);

        //todo: 걍 CreateKeyspaceSpecification.createKeyspace 만 해도 될듯?
        cqlSessionFactoryBean.setKeyspaceCreations(
            Collections.singletonList(
                CreateKeyspaceSpecification.createKeyspace(keyspaceName)
                    .ifNotExists()
                    .withSimpleReplication(1)
            )
        );
        return cqlSessionFactoryBean;
    }

    @Override
    @Nonnull
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE;
    }

    @NonNull
    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    @Nonnull
    public String[] getEntityBasePackages() {
        return new String[] {"beyondeyesight.chat.domain"};
    }
}
