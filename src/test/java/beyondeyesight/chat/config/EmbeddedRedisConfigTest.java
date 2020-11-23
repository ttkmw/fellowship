package beyondeyesight.chat.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {EmbeddedRedisConfig.class})
class EmbeddedRedisConfigTest {

    @Autowired
    EmbeddedRedisConfig embeddedRedisConfig;

    @DisplayName("di : should be injected")
    @Test
    void dependencyInjection() {
        assertThat(embeddedRedisConfig).isNotNull();
    }
}
