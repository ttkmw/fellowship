package beyondeyesight.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker // 스프링 클라우드에 이 서비스에서 히스트릭스를 사용할 것이라고 지정
@RefreshScope
public class FellowshipApplication {
    public static void main(String[] args) {
        SpringApplication.run(FellowshipApplication.class, args);
    }

}
