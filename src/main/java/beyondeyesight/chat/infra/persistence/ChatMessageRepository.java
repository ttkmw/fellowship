package beyondeyesight.chat.infra.persistence;

import beyondeyesight.chat.domain.ChatMessage;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends CassandraRepository<ChatMessage, UUID> {

    //todo: test
    ChatMessage findChatMessagesByChatRoomId(UUID chatRoomId);

    void deleteById(UUID id);

}
