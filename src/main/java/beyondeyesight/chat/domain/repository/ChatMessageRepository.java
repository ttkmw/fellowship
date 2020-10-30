package beyondeyesight.chat.domain.repository;

import beyondeyesight.chat.domain.model.ChatMessage;


public interface ChatMessageRepository {

    ChatMessage save(ChatMessage chatMessage);
}
