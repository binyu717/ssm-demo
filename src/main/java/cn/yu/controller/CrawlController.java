package cn.yu.controller;

import cn.yu.model.CrawlRecord;
import cn.yu.services.CrawlRecordService;
import cn.yu.utils.response.PageInfo;
import cn.yu.utils.response.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bin.yu
 * @create 2018-08-18 13:25
 **/
@Controller
@RequestMapping("crawl")
public class CrawlController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CrawlRecordService recordService;


    @ResponseBody
    @RequestMapping("beginCrawl")
    public ResponseInfo beginCrawl(String url) {
        try {
            recordService.beginCrawl(url);
            return ResponseInfo.success("");
        } catch (Exception e) {
            return ResponseInfo.error("爬取异常"+e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("queryCrawlData")
    public ResponseInfo queryCrawlData(Integer pageNo,Integer pageSize){
        try {
            PageInfo<CrawlRecord> pageInfo = recordService.queryCrawlRecord(pageNo, pageSize);
            return ResponseInfo.success(pageInfo);
        } catch (Exception e) {
            logger.error("获取爬虫数据异常:{}" + e.getMessage() + e);
            return ResponseInfo.error("获取爬虫数据异常" + e.getMessage());
        }
    }
}
