package beyondeyesight.chat.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdTest {

    @DisplayName("#of() : should return new Id")
    @Test
    void of() {
        String testId = "testId";
        Id id = Id.of(testId);
        assertThat(id).isNotNull();
    }
}