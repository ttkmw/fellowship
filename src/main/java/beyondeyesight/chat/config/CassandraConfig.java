package beyondeyesight.chat.config;

import java.util.Collections;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Profile("local")
@Configuration
@EnableCassandraRepositories(basePackages = "beyondeyesight.chat.infra.persistence")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.local-datacenter}")
    private String localDatacenter;

    @Override
    @Bean
    @Nonnull
    public CqlSessionFactoryBean cassandraSession() {
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
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    @Nonnull
    public SchemaAction getSchemaAction() {
        //todo: 운영시 스키마 정책 확인
        return SchemaAction.RECREATE;
    }

    @Override
    @Nonnull
    public String[] getEntityBasePackages() {
        return new String[] {"beyondeyesight.chat.domain"};
    }
}
