package cn.yu.model;

import java.util.Date;

/**
 * @author bin.yu
 * @create 2018-01-18 14:25
 *
 * 通知发送关系
 **/
public class SystemNoticeModel {

    private String id;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 通知ID
     */
    private String noticeTemplateId;

    /**
     * 内容
     */
    private String content;

    /**
     * 通知类型
     */
    private String type;

    /**
     * 接收人账号ID
     */
    private String receiveAccId;

    /**
     * 操作人账号ID
     */
    private String operAccId;

    /**
     * 被操作人ID
     */
    private String targetLinkId;

    /**
     * 来源
     */
    private String source;

    /**
     * 是否已读 true/false
     */
    private String isRead;

    /**
     * 是否删除 true/false
     */
    private String isDeleted;

    private Date gmtCreate;

    private Date gmtUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getNoticeTemplateId() {
        return noticeTemplateId;
    }

    public void setNoticeTemplateId(String noticeTemplateId) {
        this.noticeTemplateId = noticeTemplateId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiveAccId() {
        return receiveAccId;
    }

    public void setReceiveAccId(String receiveAccId) {
        this.receiveAccId = receiveAccId;
    }

    public String getOperAccId() {
        return operAccId;
    }

    public void setOperAccId(String operAccId) {
        this.operAccId = operAccId;
    }

    public String getTargetLinkId() {
        return targetLinkId;
    }

    public void setTargetLinkId(String targetLinkId) {
        this.targetLinkId = targetLinkId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}
