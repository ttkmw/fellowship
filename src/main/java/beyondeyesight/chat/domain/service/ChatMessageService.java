package beyondeyesight.chat.domain.service;

import beyondeyesight.chat.domain.adapter.MessagePublisher;
import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final MessagePublisher messagePublisher;

    public ChatMessageService(
        ChatMessageRepository chatMessageRepository, MessagePublisher messagePublisher) {
        this.chatMessageRepository = chatMessageRepository;
        this.messagePublisher = messagePublisher;
    }

    //todo: test
    @Transactional
    public void send(ChatMessage chatMessage) {
        chatMessage = chatMessageRepository.save(chatMessage);
        messagePublisher.publish(chatMessage);
    }
}
