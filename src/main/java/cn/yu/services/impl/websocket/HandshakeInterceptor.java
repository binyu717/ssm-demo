package cn.yu.services.impl.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author bin.yu
 * @create 2018-01-21 14:59
 **/
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String sessionId = servletRequest.getSession().getId();
        logger.info("beforeHandshake 握手链接：sessionId="+sessionId);
        attributes.put("sessionId", sessionId);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
