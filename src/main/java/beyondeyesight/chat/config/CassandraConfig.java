package beyondeyesight.chat.config;

import javax.annotation.Nonnull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;


@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    //todo: check 이거 없어도 될거같은디...
    @Override
    @Bean
    @Nonnull
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        //todo: config file로 옮기기
        cqlSessionFactoryBean.setContactPoints("127.0.0.1");
        cqlSessionFactoryBean.setPort(9042);
        //todo: datacenter 이름 지정해주기
        cqlSessionFactoryBean.setLocalDatacenter("datacenter1");
        return cqlSessionFactoryBean;
    }

    @Override
    @Bean
    @Nonnull
    public CassandraMappingContext cassandraMapping() {
        return new CassandraMappingContext();
    }

    @Override
    @Nonnull
    protected String getKeyspaceName() {
        return "testKeySpace";
    }
}
