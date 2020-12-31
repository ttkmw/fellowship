package beyondeyesight.chat.infra.persistence;

import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.repository.ChatMessageRepository;
import org.springframework.stereotype.Repository;


@Repository
public class ChatMessageRepositoryImpl implements ChatMessageRepository {

    private final CassandraChatMessageRepository cassandraChatMessageRepository;

    public ChatMessageRepositoryImpl(
        CassandraChatMessageRepository cassandraChatMessageRepository) {
        this.cassandraChatMessageRepository = cassandraChatMessageRepository;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return cassandraChatMessageRepository.save(chatMessage);
    }
}
