package beyondeyesight.chat.ui;

import beyondeyesight.chat.domain.model.ChatMessage;
import beyondeyesight.chat.domain.model.ChatRoom;
import beyondeyesight.chat.domain.service.ChatMessageService;
import beyondeyesight.chat.domain.service.ChatRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat/message")
    public void send(ChatMessage chatMessage) {
        //todo: implement
        chatMessageService.send(chatMessage);
    }

    @RequestMapping("/chat/rooms")
    @ResponseBody
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAll();
    }
}
