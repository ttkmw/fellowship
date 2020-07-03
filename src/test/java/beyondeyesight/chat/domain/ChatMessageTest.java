package beyondeyesight.chat.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChatMessageTest {

    @DisplayName("#of() : should return new ChatMessage")
    @Test
    void of() {
        ChatRoom mockChatRoom = mock(ChatRoom.class);
        Sender mockSender = mock(Sender.class);
        ChatMessage chatMessage = ChatMessage.of(mockChatRoom, mockSender);
        assertThat(chatMessage).isNotNull();
    }
}