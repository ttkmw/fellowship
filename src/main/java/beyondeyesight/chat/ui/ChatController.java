package beyondeyesight.chat.ui;

import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/message")
    public void send(ChatMessage chatMessage) {
        //todo: implement
    }
}
