package beyondeyesight.chat.infra.persistence;

import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.repository.ChatMessageRepository;
import org.springframework.stereotype.Repository;


@Repository
public class ChatMessageCassandraRepositoryProxy implements ChatMessageRepository {

    private final ChatMessageCassandraRepository chatMessageCassandraRepository;

    public ChatMessageCassandraRepositoryProxy(
        ChatMessageCassandraRepository chatMessageCassandraRepository) {
        this.chatMessageCassandraRepository = chatMessageCassandraRepository;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageCassandraRepository.save(chatMessage);
    }
}
