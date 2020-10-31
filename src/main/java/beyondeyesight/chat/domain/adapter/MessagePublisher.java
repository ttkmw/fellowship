package beyondeyesight.chat.domain.adapter;

import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.model.ChatRoom;

public interface MessagePublisher {
    void publish(ChatRoom chatRoom, ChatMessage chatMessage);
}
