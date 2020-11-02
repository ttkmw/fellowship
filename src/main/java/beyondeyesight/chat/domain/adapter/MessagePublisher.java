package beyondeyesight.chat.domain.adapter;

import beyondeyesight.chat.domain.model.ChatMessage;

public interface MessagePublisher {
    void publish(ChatMessage chatMessage);
}
