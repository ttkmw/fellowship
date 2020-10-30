package beyondeyesight.chat.domain.model;

import java.util.UUID;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("chat_room")
public class ChatRoom {

    @PrimaryKeyColumn(name = "room_id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
    private final UUID id;

    @PrimaryKeyColumn(name = "name", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
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
