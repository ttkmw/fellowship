package beyondeyesight.chat.domain;

import java.util.UUID;

public class RegionalChatRoom {

    private final String id;
    private final ChatRoomName name;
    private final RegionId regionId;


    private RegionalChatRoom(ChatRoomName name, RegionId regionId) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.regionId = regionId;
    }

    public static RegionalChatRoom of(ChatRoomName name, RegionId regionId) {
        return new RegionalChatRoom(name, regionId);
    }

    public static RegionalChatRoom of(String name, String regionId) {
        ChatRoomName chatRoomName = ChatRoomName.of(name);
        return of(chatRoomName, RegionId.of(regionId));
    }
}
