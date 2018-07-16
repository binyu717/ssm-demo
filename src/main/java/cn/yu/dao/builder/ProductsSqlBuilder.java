package cn.yu.dao.builder;

import cn.yu.model.Products;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author bin.yu
 * @create 2018-05-28 19:07
 **/
public class ProductsSqlBuilder {

    public String insertProduct(Products Products) {
        return new SQL() {{
            INSERT_INTO("products");
            if (StringUtils.isNotEmpty(Products.getName())) {
                VALUES("name", "#{name}");
            }
            if (Products.getCatalog() != null) {
                VALUES("catalog", "#{catalog}");
            }
            if (StringUtils.isNotEmpty(Products.getCatalogName())) {
                VALUES("catalogName", "#{catalogName}");
            }
            if (Products.getPrice() != null) {
                VALUES("price", "#{price}");
            }
            if (Products.getNumber() != null) {
                VALUES("number", "#{number}");
            }
            if (StringUtils.isNotEmpty(Products.getDescription())) {
                VALUES("description", "#{description}");
            }
            if (StringUtils.isNotEmpty(Products.getPicture())) {
                VALUES("picture", "#{picture}");
            }
            VALUES("releaseTime", "#{releaseTime}");
        }}.toString();
    }

    public String queryProducts(Products queryDo) {
        return new SQL() {{
            SELECT("*");
            FROM("products");
            if (StringUtils.isNotEmpty(queryDo.getName())) {
                WHERE("name = #{name}");
            }
            if (queryDo.getCatalog() != null) {
                WHERE("catalog=#{catalog}");
            }
            if (StringUtils.isNotEmpty(queryDo.getCatalogName())) {
                WHERE("catalogName=#{catalogName}");
            }
            if (queryDo.getPrice() != null) {
                WHERE("price=#{price}");
            }
            if (queryDo.getNumber() != null) {
                WHERE("number=#{number}");
            }
            if (StringUtils.isNotEmpty(queryDo.getDescription())) {
                WHERE("description=#{description}");
            }
            if (StringUtils.isNotEmpty(queryDo.getPicture())) {
                WHERE("picture=#{picture}");
            }
        }}.toString();
    }
}
