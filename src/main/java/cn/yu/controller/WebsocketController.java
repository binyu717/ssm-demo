package cn.yu.controller;

import cn.yu.model.SystemNoticeModel;
import cn.yu.services.impl.websocket.WebsocketSystemNoticeHandler;
import cn.yu.utils.response.ResponseInfo;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * websocket
 *
 * @author bin.yu
 * @create 2018-08-23 14:47
 **/
@Controller
@RequestMapping("message/")
public class WebsocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebsocketController.class);

    @Autowired
    private WebsocketSystemNoticeHandler websocketHandler;

    @RequestMapping("sendMessage")
    @ResponseBody
    public ResponseInfo sendMessageToUser(HttpServletRequest request, String message) {
        try {
            HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", message);
            websocketHandler.sendMessageToUser(session, jsonObject);
            return ResponseInfo.success("消息发送成功");
        } catch (Exception e) {
            logger.error("消息发送失败:{}", e.getMessage(), e);
            return ResponseInfo.error("消息发送失败");
        }
    }

    @RequestMapping("sendMessageToAll")
    @ResponseBody
    public ResponseInfo sendMessageToAll(HttpServletRequest request, String message) {
        try {
            HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", message);
            SystemNoticeModel systemNoticeModel = new SystemNoticeModel();
            systemNoticeModel.setContent(message);
            websocketHandler.sendMessageToUsers(systemNoticeModel);
            return ResponseInfo.success("消息发送成功");
        } catch (Exception e) {
            logger.error("消息发送失败:{}", e.getMessage(), e);
            return ResponseInfo.error("消息发送失败");
        }
    }
}
