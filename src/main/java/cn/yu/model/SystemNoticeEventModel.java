package cn.yu.model;

/**
 * @author bin.yu
 * @create 2018-01-19 11:59
 **/
public class SystemNoticeEventModel {


    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 场景
     */
    private String scene;

    /**
     * 通知类型
     */
    private String type;

    /**
     * 关联ID
     */
    private String linkedId;

    /**
     * 操作账号ID
     */
    private String operAccId;

    /**
     * 拓展信息
     */
    private Object extData;

    public Object getExtData() {
        return extData;
    }

    public void setExtData(Object extData) {
        this.extData = extData;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(String linkedId) {
        this.linkedId = linkedId;
    }

    public String getOperAccId() {
        return operAccId;
    }

    public void setOperAccId(String operAccId) {
        this.operAccId = operAccId;
    }

}
