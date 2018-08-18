package cn.yu.services.impl;

import cn.yu.dao.CrawlRecordDao;
import cn.yu.model.CrawlRecord;
import cn.yu.services.CrawlRecordService;
import cn.yu.utils.response.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author bin.yu
 * @create 2018-08-18 12:51
 **/
@Service
public class CrawlRecordServiceImpl implements CrawlRecordService {

    @Autowired
    private CrawlRecordDao crawlRecordDao;


    @Override
    public void cleanHistories() {
        crawlRecordDao.truncateRecord();
    }

    @Override
    public void beginCrawl(String url) throws IOException {
        //检查一下是否给定的URL已经在数据库中
        CrawlRecord record = crawlRecordDao.findByUrl(url);
        if (record == null) {
            Document doc = Jsoup.connect(url).get();
            Elements news = doc.select("div.main").select("li").select("a[href]");
            for (Element element : news) {
                String title = element.text();
                String childUrl = element.attr("abs:href");
                if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(childUrl)) {
                    CrawlRecord crawlRecord = new CrawlRecord();
                    crawlRecord.setUrl(url);
                    crawlRecord.setTitle(title);
                    crawlRecord.setCreatedTime(new java.util.Date());
                    crawlRecordDao.insertCrawlRecord(crawlRecord);
                    beginCrawl(element.attr("abs:href"));
                }
            }
        }
    }

    @Override
    public PageInfo<CrawlRecord> queryCrawlRecord(Integer pageNo, Integer pageSize) {
        return crawlRecordDao.queryCrawlRecord(pageNo,pageSize);
    }
}
