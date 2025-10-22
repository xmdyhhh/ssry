package com.ruoyi.studentSys.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request,
        HandshakeResponse response) {

        HttpSession httpSession = (HttpSession)request.getHttpSession();
        //将 httpsession 存入 ServerEndpointConfig对象，方便于websocket在OnOpen()建立链接后获取
        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }


}