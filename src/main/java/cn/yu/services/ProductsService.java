package cn.yu.services;

import cn.yu.model.Products;

import java.util.List;

/**
 * @author bin.yu
 * @create 2018-05-28 19:43
 **/
public interface ProductsService {

    Integer insertProduct(Products productsModel);

    List<Products> queryProducts(Products queryModel);
}
