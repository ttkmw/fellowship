package beyondeyesight.chat.infra.persistence;

import beyondeyesight.chat.domain.model.ChatRoom;
import beyondeyesight.chat.domain.repository.ChatRoomRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomCassandraRepositoryProxy implements ChatRoomRepository {

    private final ChatRoomCassandraRepository chatRoomCassandraRepository;

    public ChatRoomCassandraRepositoryProxy(
        ChatRoomCassandraRepository chatRoomCassandraRepository) {
        this.chatRoomCassandraRepository = chatRoomCassandraRepository;
    }

    @Override
    public List<ChatRoom> findAll() {
        return chatRoomCassandraRepository.findAll();
    }
}
