package cn.yu.utils.evnt;

import com.google.common.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;


public final class BusHolder {

    private static final Map<BusName, EventBus> eventBusMap = new HashMap<>();
    //员工模块的总线

    static {
        for (BusName name : BusName.values()) {
            eventBusMap.put(name, new EventBus(name.getBusName()));
        }
    }

    /**
     * 注册监听器到总线上
     *
     * @param busName
     * @param listener
     */
    public static void register(BusName busName, Object listener) {
        EventBus bus = eventBusMap.get(busName);
        bus.register(listener);
    }

    public static void post(BusName busName, Object event) {
        EventBus bus = eventBusMap.get(busName);
        bus.post(event);
    }

}
