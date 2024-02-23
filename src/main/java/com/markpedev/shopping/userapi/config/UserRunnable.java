package com.markpedev.shopping.userapi.config;

import java.util.List;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.markpedev.shopping.userapi.dto.UserDTO;
import com.markpedev.shopping.userapi.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRunnable implements Runnable{
	private WebSocketSession session;
    private ObjectMapper objectMapper;
    private UserService userService;
    
    @Override
    public void run() {
    	System.out.println(session);
        List<UserDTO> usuarios = userService.getAll();
        try {
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(usuarios));
            session.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
