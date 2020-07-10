package beyondeyesight.chat.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TestTravisCiTest {

    @Test
    void output() {
        TestTravisCi testTravisCi = new TestTravisCi();
        int output = testTravisCi.output(2);
        assertThat(output).isEqualTo(2);
    }
}