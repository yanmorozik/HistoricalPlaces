package eu.morozik.historicalplaces.websocket.handler;

import eu.morozik.historicalplaces.controller.AuthenticationController;
import eu.morozik.historicalplaces.dto.userdto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private AuthenticationController authenticationController;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("connection established"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ResponseEntity<UserDto> dto = authenticationController.register(UserDto.builder().id(Long.valueOf(message.getPayload())).build());
        session.sendMessage(new TextMessage("user id:"+ dto.toString()));
    }
}
