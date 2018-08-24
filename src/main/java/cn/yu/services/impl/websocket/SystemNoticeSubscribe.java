package cn.yu.services.impl.websocket;


import cn.yu.model.SystemNoticeEventModel;
import cn.yu.model.SystemNoticeModel;
import cn.yu.utils.evnt.BusHolder;
import cn.yu.utils.evnt.BusName;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author bin.yu
 * @create 2018-01-18 17:12
 *
 * 通知发送
 **/
@Component
public class SystemNoticeSubscribe {

    private static final Logger logger = LoggerFactory.getLogger(SystemNoticeSubscribe.class);



    @Autowired
    private WebsocketSystemNoticeHandler websocketHandler;





    public SystemNoticeSubscribe() {

        BusHolder.register(BusName.SYSTEM_NOTICE, new Object() {
            @Subscribe
            public void lister(SystemNoticeEventModel eventModel) {
                try {
                    insertNotice(eventModel);
                } catch (Exception e) {
                    logger.error("新增通知出错 ", e.getMessage(), e);
                    throw e;
                }
            }
        });

    }

    private void insertNotice(SystemNoticeEventModel eventModel) {
        try {
            // TODO: 2018/8/22 项目业务逻辑
            SystemNoticeModel systemNoticeDo = new SystemNoticeModel();
            websocketHandler.sendMessageToUsers(systemNoticeDo);
        } catch (Exception ex) {
            logger.error("新增通知异常:"+ex.getMessage());
        }
    }



}
