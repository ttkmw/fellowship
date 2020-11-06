package beyondeyesight.chat.domain.model;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;


//todo: check if serializable is needed
@Data
@Table
public class ChatMessage {

    @PrimaryKeyColumn(name = "msg_id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
    private UUID id;

    //todo: ChatRoom으로 해도 될지(id 말고).
    @PrimaryKeyColumn(name = "room_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID chatRoomId;
    private Sender sender;
    @Column
    private String body;

    //deserialize를 위해 필
    private ChatMessage() {}

    private ChatMessage(UUID id, UUID chatRoomId, Sender sender, String body) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.body = body;
    }


    public static ChatMessage of(UUID id, UUID chatRoomId, Sender sender, String body) {
        return new ChatMessage(id, chatRoomId, sender, body);
    }

    public String getChatRoomId() {
        return chatRoomId.toString();
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
            "id=" + id +
            ", chatRoomId=" + chatRoomId +
            ", sender=" + sender +
            ", body='" + body + '\'' +
            '}';
    }
}
