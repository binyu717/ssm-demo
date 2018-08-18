package cn.yu.model;

import java.util.Date;

/**
 * 爬虫
 *
 * @author bin.yu
 * @create 2018-08-18 10:56
 **/
public class CrawlRecord {

    private Integer id;
    private String url;
    private String title;
    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
