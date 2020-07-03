package beyondeyesight.chat.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RegionalChatRoomTest {

    @DisplayName("#of() : should return new RegionalChatRoom")
    @Test
    void of() {
        //given
        String name = "testRoomName";
        String regionId = "testRegionId";
        //when
        RegionalChatRoom regionalChatRoom = RegionalChatRoom.of(name, regionId);
        //then
        assertThat(regionalChatRoom).isNotNull();
    }
}