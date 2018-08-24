package cn.yu.services.impl.websocket;

import cn.yu.model.SystemNoticeModel;
import cn.yu.utils.response.ResponseInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author bin.yu
 * @create 2018-01-21 14:58
 **/
public class WebsocketSystemNoticeHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);
    private static Cache<String,WebSocketSession> cache;
    public WebsocketSystemNoticeHandler() {
        super();
        cache = CacheBuilder.newBuilder()
                //允许同时最多10个线程并发修改
                .concurrencyLevel(10)
                //最大缓存10000个对象
                .maximumSize(10000L)
                //使用SoftReference对象封装value, 使得内存不足时，自动回收
                .softValues()
                .expireAfterWrite(6L, TimeUnit.MINUTES).removalListener(new RemovalListener<String,WebSocketSession>() {
                    @Override
                    public void onRemoval(RemovalNotification<String,WebSocketSession> notification) {
                        if ("EXPIRED".equals(notification.getCause())) {
                            logger.debug("缓存超时过期："+notification.getValue());
                        } else if ("COLLECTED".equals(notification.getCause())) {
                            logger.debug("缓存被垃圾回收："+notification.getValue());
                        }
                    }
                }).build();
    }

    /**
     * 连接成功
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String,String> info = getInfo(session);
        cache.put(info.get("sessionId"),session);
        logger.debug("------------ WebSocketSession afterConnectionEstablished sessionId="+info.get("sessionId"));
    }

    /**
     * 连接关闭
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Map<String,String> info = getInfo(session);
        cache.invalidate(info.get("sessionId"));
        logger.debug("------------ WebSocketSession afterConnectionClosed sessionId="+info.get("sessionId"));

    }

    /**
     * 异常处理
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        Map<String,String> info = getInfo(session);
        cache.invalidate(info.get("sessionId"));
        logger.debug("------------ WebSocketSession handleTransportError"+info.get("sessionId"));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        Map<String,String> info = getInfo(session);
        ResponseInfo pingMessage = new ResponseInfo();
        pingMessage.setSuccess(Boolean.FALSE);
        pingMessage.setMessage(message.toString());
        TextMessage textMessage = new TextMessage(JSON.toJSONString(pingMessage));
        session.sendMessage(textMessage);
        //更新缓存
        cache.put(info.get("sessionId"),session);
        logger.debug("------------ WebSocketSession 接收到的消息 "+message.getPayload()+",sessionId" +info.get("sessionId"));
    }

    public void sendMessageToUser(HttpSession session  , JSONObject data) {
        TextMessage message = new TextMessage(JSON.toJSONString(data));
        try {
            WebSocketSession webSocketSession = cache.getIfPresent(session.getId());
            if (webSocketSession != null) {
                webSocketSession.sendMessage(message);
            }
        } catch (IOException ex) {
            logger.debug("------------WebSocketSession发送消息异常：{}",ex.getMessage());
        }
    }

    public void sendMessageToUsers(SystemNoticeModel systemNoticeDo){
        String receiveAccId = systemNoticeDo.getReceiveAccId();
        Collection<WebSocketSession> users = cache.asMap().values();
       for (WebSocketSession user : users) {
            try {
                ResponseInfo notice = new ResponseInfo();
                notice.setSuccess(Boolean.TRUE);
                Map dataMap = new HashMap(2);
                dataMap.put("type", systemNoticeDo.getType());
                dataMap.put("content", systemNoticeDo.getContent());
                notice.setData(dataMap);
                    TextMessage message = new TextMessage(JSON.toJSONString(notice));
                    user.sendMessage(message);

            } catch (Exception ex) {
                logger.debug("------------WebSocketSession群发通知异常：{}", ex.getMessage(), ex);
            }
        }

    }

    public void getAllClients(){
        List<String> sessionIds = new ArrayList<>();
        ConcurrentMap<String, WebSocketSession> map = cache.asMap();
        Collection<WebSocketSession> users = map.values();
        for (Map.Entry<String, WebSocketSession> entry : map.entrySet()) {
            sessionIds.add(entry.getKey());
        }
        try {
            for (WebSocketSession user : users) {
                JSONObject msg = new JSONObject();
                msg.put("sessionIds", sessionIds);
                TextMessage message = new TextMessage(msg.toJSONString());
                user.sendMessage(message);
            }
        } catch (IOException e) {
            logger.debug("WebSocket推送在线人数异常：{}", e.getMessage(), e);
        }
    }



    private Map<String,String> getInfo(WebSocketSession session) {
        Map<String,String> result = new HashMap(3);
        String companyId = (String) session.getAttributes().get("companyId");
        String sessionId = (String) session.getAttributes().get("sessionId");
        String accountId = (String)session.getAttributes().get("accountId");
        result.put("companyId", companyId);
        result.put("sessionId", sessionId);
        result.put("accountId", accountId);
        return result;
    }



}
