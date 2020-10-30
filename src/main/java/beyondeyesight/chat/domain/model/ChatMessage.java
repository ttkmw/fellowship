package beyondeyesight.chat.domain.model;

import java.util.UUID;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;


//todo: check if serializable is needed
@Setter
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

    private ChatMessage(UUID chatRoomId, Sender sender, String body) {
        //todo: 애플리케이션 레벨에서는 id 안넣어도 db 레벨에서 넣어주는지 확인
        this.id = UUID.randomUUID();
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.body = body;
    }

    public static ChatMessage of(UUID chatRoomId, Sender sender, String body) {
        return new ChatMessage(chatRoomId, sender, body);
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
