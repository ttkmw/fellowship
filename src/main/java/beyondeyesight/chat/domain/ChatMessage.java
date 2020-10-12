package beyondeyesight.chat.domain;

import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

//todo: check if serializable is needed
@Table
public class ChatMessage {

    @PrimaryKey
    private final UUID id;
    //todo: 이거 확인. converting..?
//    @CassandraType(type = Name.UDT, userTypeName = "chatRoom")
    //todo: ChatRoom 자체로 할지, ChatRoomId로 할지 고민.
    private final ChatRoom chatRoom;
    private final Sender sender;
    @Column
    private final String body;

    private ChatMessage(ChatRoom chatRoom, Sender sender, String body) {
        //todo: 애플리케이션 레벨에서는 id 안넣어도 db 레벨에서 넣어주는지 확인
        this.id = UUID.randomUUID();
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.body = body;
    }

    public static ChatMessage of(ChatRoom chatRoom, Sender sender, String body) {
        return new ChatMessage(chatRoom, sender, body);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
            "id=" + id +
            ", chatRoom=" + chatRoom +
            ", sender=" + sender +
            ", body='" + body + '\'' +
            '}';
    }
}
