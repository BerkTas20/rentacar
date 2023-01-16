package com.berktas.rentacar.core.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class HandshakeInterceptorImpl implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if(serverHttpRequest instanceof ServletServerHttpRequest){
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            //HttpSession session = request.getServletRequest().getSession();
            //SessionAdapter sessionAdapter = new SessionAdapter(session);
            //Company company = sessionAdapter.getCompany();
            //User user = sessionAdapter.getUser();
            //map.put("company",company);
            //map.put("user",user);
            //map.put("sessionId", session.getId());
            //map.put("HTTPSESSIONID", session.getId());
            return true;

        }

        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }

}
