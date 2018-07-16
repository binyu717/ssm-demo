package cn.yu.dao.mapper;

import cn.yu.dao.builder.ProductsSqlBuilder;
import cn.yu.model.Products;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 产品mapper
 *
 * @author bin.yu
 * @create 2018-05-28 18:46
 **/
public interface ProductsMapper {

    @InsertProvider(type = ProductsSqlBuilder.class,method = "insertProduct")
    Integer insertProduct(Products productsDo);

    @SelectProvider(type = ProductsSqlBuilder.class,method = "queryProducts")
    List<Products> queryProducts(Products queryDo);
}
