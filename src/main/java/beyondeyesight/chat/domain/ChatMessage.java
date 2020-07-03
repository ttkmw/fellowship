package beyondeyesight.chat.domain;

public class ChatMessage {
    private final ChatRoom chatRoom;
    private final Sender sender;
    private final String body;

    public ChatMessage(ChatRoom chatRoom, Sender sender, String body) {
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.body = body;
    }

    static ChatMessage of(ChatRoom chatRoom, Sender sender) {
        return new ChatMessage(chatRoom, sender, "body");
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public Sender getSender() {
        return sender;
    }
}
