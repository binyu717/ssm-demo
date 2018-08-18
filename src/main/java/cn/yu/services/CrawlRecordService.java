package cn.yu.services;

import cn.yu.model.CrawlRecord;
import cn.yu.utils.response.PageInfo;

import java.io.IOException;

/**
 * @author bin.yu
 * @create 2018-08-18 12:51
 **/
public interface CrawlRecordService {

    void cleanHistories();

    void beginCrawl (String url) throws IOException;


    PageInfo<CrawlRecord> queryCrawlRecord(Integer pageSize, Integer pageNo);
}
