package cn.yu.services.impl.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 如果采用xml配置，则要把该配置放到spring-servlet上加载
 * 但是在spring-context加载的时候，messageHandler是还没有初始化的，其他类中不能自动注入handler，否则服务启动失败。
 * @author bin.yu
 * @create 2018-08-23 17:08
 **/
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {
    //也可自动注入 在WebsocketSystemNoticeHandler加注解就行了
//    @Autowired
//    private WebsocketSystemNoticeHandler websocketSystemNoticeHandler;
    @Bean
    public WebsocketSystemNoticeHandler websocketSystemNoticeHandler() {
        return new WebsocketSystemNoticeHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(websocketSystemNoticeHandler(), "/websocket")
                .setAllowedOrigins("*")
        .addInterceptors(new HandshakeInterceptor());

    }
}
