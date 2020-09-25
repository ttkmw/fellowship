package beyondeyesight.chat.domain;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
//todo: check if serializable is needed
@Table
public class ChatMessage implements Serializable {
    //todo: 얘도 id가 있어야 함.
    //todo: ChatRoom 자체로 할지, ChatRoomId로 할지 고민.
    @PrimaryKey
    //todo: final 넣기
    private  String id;
    @Column
    private  String chatRoom;
    @Column
    private  String sender;
    @Column
    private  String body;

    private ChatMessage(String chatRoom, String sender, String body) {
        //todo: check
        this.id = UUID.randomUUID().toString();
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.body = body;
    }

    public static ChatMessage of(String chatRoom, String sender, String body) {
        return new ChatMessage(chatRoom, sender, body);
    }
}
