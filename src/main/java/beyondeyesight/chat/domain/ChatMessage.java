package beyondeyesight.chat.domain;

public class ChatMessage {
    private ChatRoom chatRoom;
    private Sender sender;
    private String body;

    public ChatMessage(String body) {
        this.body = body;
    }

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
