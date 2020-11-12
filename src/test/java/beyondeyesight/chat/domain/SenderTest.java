package beyondeyesight.chat.domain;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.chat.domain.model.Sender;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SenderTest {

    @DisplayName("#of() : should return new Sender")
    @Test
    void of() {
        Sender sender = Sender.of(UUID.randomUUID());
        assertThat(sender).isNull();
    }
}
