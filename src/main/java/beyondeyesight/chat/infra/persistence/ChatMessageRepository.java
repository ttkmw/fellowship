package beyondeyesight.chat.infra.persistence;

import beyondeyesight.chat.domain.ChatMessage;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends CassandraRepository<ChatMessage, String> {

}
