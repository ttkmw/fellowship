package beyondeyesight.chat.domain;

import java.util.UUID;

public class ChatRoom {

    private final Id id;
    private final ChatRoomName name;


    private ChatRoom(ChatRoomName name) {
        //todo: region 서비스를 만들면, id를 받아서 생성
        this.id = Id.of(UUID.randomUUID().toString());
        this.name = name;
    }

    public static ChatRoom of(ChatRoomName name) {
        return new ChatRoom(name);
    }

    public static ChatRoom of(String name) {
        ChatRoomName chatRoomName = ChatRoomName.of(name);
        return of(chatRoomName);
    }
}
