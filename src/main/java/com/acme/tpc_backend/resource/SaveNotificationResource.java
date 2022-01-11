package com.acme.tpc_backend.resource;

import java.util.Date;

public class SaveNotificationResource{
    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long notificationTypeId) {
       this.notificationTypeId = notificationTypeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    private Long notificationTypeId;
    private String content;
    private String link;
    private Date sendDate;
}