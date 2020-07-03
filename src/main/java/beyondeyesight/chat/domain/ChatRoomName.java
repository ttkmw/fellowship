package beyondeyesight.chat.domain;

public class ChatRoomName {
    private final String name;

    private ChatRoomName(String name) {
        this.name = name;
    }

    static ChatRoomName of(String name) {
        return new ChatRoomName(name);
    }
}
