package beyondeyesight.chat.domain;

import java.util.UUID;

public class RegionalChatRoom implements ChatRoom {

    private final Id id;
    private final ChatRoomName name;
    private final Id regionalId;


    private RegionalChatRoom(ChatRoomName name, Id regionalId) {
        this.id = Id.of(UUID.randomUUID().toString());
        this.name = name;
        this.regionalId = regionalId;
    }

    public static RegionalChatRoom of(ChatRoomName name, Id regionalId) {
        return new RegionalChatRoom(name, regionalId);
    }

    public static RegionalChatRoom of(String name, String regionId) {
        ChatRoomName chatRoomName = ChatRoomName.of(name);
        return of(chatRoomName, Id.of(regionId));
    }
}
