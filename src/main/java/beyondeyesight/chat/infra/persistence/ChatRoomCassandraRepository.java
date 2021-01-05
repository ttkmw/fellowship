package beyondeyesight.chat.infra.persistence;

import beyondeyesight.chat.domain.model.ChatRoom;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ChatRoomCassandraRepository extends CassandraRepository<ChatRoom, UUID> {

}
