package com.markpedev.shopping.userapi.config;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.markpedev.shopping.userapi.service.UserService;

public class UserWebSocketHandler extends TextWebSocketHandler {
	private ScheduledFuture<?> scheduledFuture;
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    
    @Autowired
    private UserService userService;
   
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	
    	UserRunnable userRunnable = new UserRunnable(session, objectMapper, userService);
    	UserRunnableManager.setUserRunnable(userRunnable);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        scheduledFuture = executor.scheduleAtFixedRate(userRunnable, 0, 30, TimeUnit.SECONDS);
    	
        sessions.add(session);
        
        
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        session.close();
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }
}