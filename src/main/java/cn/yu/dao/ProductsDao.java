package cn.yu.dao;

import cn.yu.model.Products;

import java.util.List;

/**
 * @author bin.yu
 * @create 2018-05-28 19:40
 **/
public interface ProductsDao {

    Integer insertProduct(Products productsDo);

    List<Products> queryProducts(Products queryDo);
}
