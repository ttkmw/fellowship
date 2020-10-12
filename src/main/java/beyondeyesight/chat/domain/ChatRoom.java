package beyondeyesight.chat.domain;

import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("chatroom")
public class ChatRoom {

    private final UUID id;

    private final String name;

    private ChatRoom(String name) {
        //todo: region 서비스를 만들면, id를 받아서 생성
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public static ChatRoom of(String name) {
        return new ChatRoom(name);
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
