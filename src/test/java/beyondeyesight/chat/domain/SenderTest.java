package beyondeyesight.chat.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SenderTest {

    @DisplayName("#of() : should return new Sender")
    @Test
    void of() {
        String testId = "testId";
        Sender sender = Sender.of(testId);
        assertThat(sender).isNotNull();
    }
}