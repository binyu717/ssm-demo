package cn.yu.dao;

import cn.yu.model.CrawlRecord;
import cn.yu.utils.response.PageInfo;

/**
 * @author bin.yu
 * @create 2018-08-18 12:41
 **/
public interface CrawlRecordDao {


    void insertCrawlRecord(CrawlRecord crawlRecord);

    CrawlRecord findByUrl(String url);

    void truncateRecord();

    PageInfo<CrawlRecord> queryCrawlRecord(Integer pageSize, Integer pageNo);
}
