package beyondeyesight.chat.infra.persistence;

import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ChatMessageRepositoryImpl implements ChatMessageRepository {

    @Autowired
    private CassandraChatMessageRepository cassandraChatMessageRepository;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return cassandraChatMessageRepository.save(chatMessage);
    }
}
