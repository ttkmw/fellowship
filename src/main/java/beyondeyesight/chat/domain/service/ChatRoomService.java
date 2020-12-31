package beyondeyesight.chat.domain.service;

import beyondeyesight.chat.domain.model.ChatRoom;
import beyondeyesight.chat.domain.repository.ChatRoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> getAll() {
        return chatRoomRepository.findAll();
    }
}
