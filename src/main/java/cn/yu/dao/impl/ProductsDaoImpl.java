package cn.yu.dao.impl;

import cn.yu.dao.ProductsDao;
import cn.yu.dao.mapper.ProductsMapper;
import cn.yu.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bin.yu
 * @create 2018-05-28 19:41
 **/
@Component
public class ProductsDaoImpl implements ProductsDao {

    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public Integer insertProduct(Products productsDo) {
        return productsMapper.insertProduct(productsDo);
    }

    @Override
    public List<Products> queryProducts(Products queryDo) {
        return productsMapper.queryProducts(queryDo);
    }
}
