package beyondeyesight.chat.domain;

import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

//todo: check if serializable is needed
@Table
public class ChatMessage {
    //todo: 얘도 id가 있어야 함.
    //todo: ChatRoom 자체로 할지, ChatRoomId로 할지 고민.
    @PrimaryKey
    private final UUID id;
    //todo: 이거 확인. converting..?
//    @CassandraType(type = Name.UDT, userTypeName = "chatRoom")
    private final ChatRoom chatRoom;
    private final Sender sender;
    @Column
    private final String body;

    private ChatMessage(ChatRoom chatRoom, Sender sender, String body) {
        //todo: check
        this.id = UUID.randomUUID();
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.body = body;
    }

    public static ChatMessage of(ChatRoom chatRoom, Sender sender, String body) {
        return new ChatMessage(chatRoom, sender, body);
    }
}
