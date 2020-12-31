package beyondeyesight.chat.domain.repository;

import beyondeyesight.chat.domain.model.ChatRoom;
import java.util.List;

public interface ChatRoomRepository {
    List<ChatRoom> findAll();
}
