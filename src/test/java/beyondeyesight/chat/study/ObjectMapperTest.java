package beyondeyesight.chat.study;


import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.chat.domain.model.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.Test;

public class ObjectMapperTest {

    @Test
    public void readValue() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UUID testId = UUID.randomUUID();
        String publishedMessage = "{\"id\":" + "\"" + testId + "\","
            + "\"chatRoomId\":" + "\"" + testId + "\","
            + "\"sender\":{\"id\":\"" + testId + "\"},"
            + "\"body\":\"testBody\"}";
        ChatMessage chatMessage = objectMapper.readValue(publishedMessage, ChatMessage.class);
        assertThat(chatMessage).isNotNull();
    }

}
