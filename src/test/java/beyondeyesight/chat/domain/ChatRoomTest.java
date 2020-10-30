package beyondeyesight.chat.domain;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.chat.domain.model.ChatRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChatRoomTest {

    @DisplayName("#of() : should return new RegionalChatRoom")
    @Test
    void of() {
        //given
        String name = "testRoomName";
        //when
        ChatRoom chatRoom = ChatRoom.of(name);
        //then
        assertThat(chatRoom).isNotNull();
    }
}
