package beyondeyesight.chat.config;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

/**
 * Reference: https://stackoverflow.com/questions/47373402/spring-stromp-incomplete-frame
 *
 * 이 클래스는 브라우저에서 Websocket Client를 이용해 Websocket 통신을 수작업으로 하기 위해 필요한 것입니다.
 * todo: 브라우저에서 수작업으로 테스트하는 게 아니라면 딱히 필요 없어보입니다.
 * todo: 그런데 나만 브라우저에서 Websocket 통신을 테스트해보려고 하진 않았을 것 같은데, 구글에 EmaWebSocketHandlerDecorator에 대한 설명이 부족합니다.
 * todo: 많이들 쓰면 maven-repo에 등록되어 있을 것 같은데...
 * todo: 의존성을 받는 방식으로 바꾸거나, 혹시 이거때매
 *
 * Extension of the {@link WebSocketHandlerDecorator websocket handler decorator} that allows to manually test the
 * STOMP protocol.
 *
 * @author Sebastien Gerard
 */
public class EmaWebSocketHandlerDecorator extends WebSocketHandlerDecorator {

    private static final Logger logger = LoggerFactory.getLogger(EmaWebSocketHandlerDecorator.class);
    private static final String END_OF_STRING = "\u0000";
    public static final String NEW_LINE = "\n";

    public EmaWebSocketHandlerDecorator(WebSocketHandler webSocketHandler) {
        super(webSocketHandler);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, updateBodyIfNeeded(message));
    }

    /**
     * Updates the content of the specified message. The message is updated only if it is
     * a {@link TextMessage text message} and if does not contain the <tt>null</tt> character at the end. If
     * carriage returns are missing (when the command does not need a body) there are also added.
     */
    private WebSocketMessage<?> updateBodyIfNeeded(WebSocketMessage<?> message) {
        if (!(message instanceof TextMessage) || ((TextMessage) message).getPayload().endsWith(
            END_OF_STRING)) {
            return message;
        }
        String payload = ((TextMessage) message).getPayload();
        final Optional<StompCommand> stompCommand = getStompCommand(payload);
        if (!stompCommand.isPresent()) {
            return message;
        }
        payload = settleFormOfCommandsWithoutBody(payload, stompCommand.orElseThrow(
            IllegalStateException::new));

        payload += END_OF_STRING;

        return new TextMessage(payload);
    }

    private String settleFormOfCommandsWithoutBody(String payload,
        StompCommand stompCommand) {
        if (!stompCommand.isBodyAllowed() && !payload.endsWith(NEW_LINE + NEW_LINE)) {
            if (payload.endsWith(NEW_LINE)) {
                payload += NEW_LINE;
            } else {
                payload += NEW_LINE + NEW_LINE;
            }
        }
        return payload;
    }

    /**
     * Returns the {@link StompCommand STOMP command} associated to the specified payload.
     */
    private Optional<StompCommand> getStompCommand(String payload) {
        final int firstCarriageReturn = payload.indexOf('\n');

        if (firstCarriageReturn < 0) {
            return Optional.empty();
        }

        try {
            return Optional.of(
                StompCommand.valueOf(payload.substring(0, firstCarriageReturn))
            );
        } catch (IllegalArgumentException e) {
            logger.trace("Error while parsing STOMP command.", e);

            return Optional.empty();
        }
    }
}
