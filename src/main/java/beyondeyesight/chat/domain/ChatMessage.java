package beyondeyesight.chat.domain;

//todo: check if serializable is needed
public class ChatMessage {
    //todo: 얘도 id가 있어야 함.
    //todo: ChatRoom 자체로 할지, ChatRoomId로 할지 고민.
    private final ChatRoom chatRoom;
    private final Sender sender;
    private final String body;

    private ChatMessage(ChatRoom chatRoom, Sender sender, String body) {
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.body = body;
    }

    public static ChatMessage of(ChatRoom chatRoom, Sender sender, String body) {
        return new ChatMessage(chatRoom, sender, body);
    }
}
