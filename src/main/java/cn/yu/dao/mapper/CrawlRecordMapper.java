package cn.yu.dao.mapper;

import cn.yu.model.CrawlRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author bin.yu
 * @create 2018-08-18 10:54
 **/
public interface CrawlRecordMapper {


    @Insert(" Insert into crawl_record (url,title,created_time) values (#{url},#{title},#{createdTime})")
    Integer insertCrawlRecord(CrawlRecord crawlRecord);

    @Update("TRUNCATE crawl_record ")
    void truncateRecord();

    @Select("select * from crawl_record where url = #{url} limit 0,1")
    CrawlRecord findByUrl(@Param("url") String url);

    @Select("select * from crawl_record order by id limit #{skip},#{count} ")
    List<CrawlRecord> queryCrawlRecords(@Param("skip") Integer skip, @Param("count") Integer count);

    @Select("select count(1) from crawl_record")
    Long countCrawlRecords();
}
