package beyondeyesight.chat.domain;

import java.util.UUID;

public class RegionalChatRoom {

    private final String id;
    private final ChatRoomName name;


    private RegionalChatRoom(ChatRoomName name) {
        id = UUID.randomUUID().toString();
        this.name = name;
    }

    public static RegionalChatRoom of(ChatRoomName name) {
        return new RegionalChatRoom(name);
    }

    public static RegionalChatRoom of(String name) {
        ChatRoomName chatRoomName = ChatRoomName.of(name);
        return of(chatRoomName);
    }
}
