package beyondeyesight.chat.infra.persistence;

import beyondeyesight.chat.domain.model.ChatRoom;
import beyondeyesight.chat.domain.repository.ChatRoomRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

    private final CassandraChatRoomRepository cassandraChatRoomRepository;

    public ChatRoomRepositoryImpl(
        CassandraChatRoomRepository cassandraChatRoomRepository) {
        this.cassandraChatRoomRepository = cassandraChatRoomRepository;
    }

    @Override
    public List<ChatRoom> findAll() {
        return cassandraChatRoomRepository.findAll();
    }
}
