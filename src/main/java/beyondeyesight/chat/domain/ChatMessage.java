package beyondeyesight.chat.domain;

public class ChatMessage {
    private final ChatRoom chatRoom;
    private final Sender sender;

    private ChatMessage(ChatRoom chatRoom, Sender sender) {
        this.chatRoom = chatRoom;
        this.sender = sender;
    }

    static ChatMessage of(ChatRoom chatRoom, Sender sender) {
        return new ChatMessage(chatRoom, sender);
    }
}
