package cn.yu.utils.evnt;


public enum BusName {

    SYSTEM_NOTICE("system_notice");


    private String busName;

    BusName(String busName) {
        this.busName = busName;
    }

    public String getBusName() {
        return busName;
    }

}
