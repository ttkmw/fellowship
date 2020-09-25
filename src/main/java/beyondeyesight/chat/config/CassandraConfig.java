package beyondeyesight.chat.config;

import java.util.Collections;
import javax.annotation.Nonnull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


@Configuration
//todo: basePakages가 꼭 필요한지 확인
@EnableCassandraRepositories(basePackages = "beyondeyesight.chat.infra.persistence")
public class CassandraConfig extends AbstractCassandraConfiguration {
    @Override
    @Bean
    @Nonnull
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        //todo: config file로 옮기기
        cqlSessionFactoryBean.setContactPoints("localhost");
        cqlSessionFactoryBean.setPort(9042);
        cqlSessionFactoryBean.setKeyspaceName("testKeySpace");
        //todo: datacenter 이름 지정해주기
        cqlSessionFactoryBean.setLocalDatacenter("datacenter1");

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

    @Override
    @Nonnull
    protected String getKeyspaceName() {
        return "testKeySpace";
    }

    @Override
    @Nonnull
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    @Nonnull
    public String[] getEntityBasePackages() {
        return new String[] {"beyondeyesight.chat.domain"};
    }
}
