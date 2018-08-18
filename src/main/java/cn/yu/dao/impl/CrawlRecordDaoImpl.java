package cn.yu.dao.impl;

import cn.yu.dao.CrawlRecordDao;
import cn.yu.dao.mapper.CrawlRecordMapper;
import cn.yu.model.CrawlRecord;
import cn.yu.utils.response.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bin.yu
 * @create 2018-08-18 12:44
 **/
@Component
public class CrawlRecordDaoImpl implements CrawlRecordDao {

    @Autowired
    private CrawlRecordMapper recordMapper;

    @Override
    public void insertCrawlRecord(CrawlRecord crawlRecord) {
        recordMapper.insertCrawlRecord(crawlRecord);
    }

    @Override
    public CrawlRecord findByUrl(String url) {
        return recordMapper.findByUrl(url);
    }

    @Override
    public void truncateRecord() {
        recordMapper.truncateRecord();
    }

    @Override
    public PageInfo<CrawlRecord> queryCrawlRecord(Integer pageNo,Integer pageSize) {
        PageInfo<CrawlRecord> rs = new PageInfo<>();
        int begin = 0;
        if (pageNo != null && pageSize != null) {
            begin = (pageNo - 1) * pageSize;
        }
        List<CrawlRecord> records = recordMapper.queryCrawlRecords(begin, pageSize);
        Long total = recordMapper.countCrawlRecords();
        rs.setList(records);
        rs.setPageNo(pageNo.longValue());
        rs.setPageSize(pageSize.longValue());
        rs.setTotalNum(total);
        return rs;
    }
}
