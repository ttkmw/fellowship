package beyondeyesight.chat.domain.service;

import beyondeyesight.chat.domain.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    public ChatMessageService(
        ChatMessageRepository chatMessageRepository) {
    }
}
