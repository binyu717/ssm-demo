package cn.yu.services.impl;

import cn.yu.dao.ProductsDao;
import cn.yu.model.Products;
import cn.yu.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bin.yu
 * @create 2018-05-28 19:46
 **/
@Service
public class ProducsServiceImpl implements ProductsService {

    @Autowired
    private ProductsDao productsDao;

    @Override
    public Integer insertProduct(Products productsModel) {
        return productsDao.insertProduct(productsModel);
    }

    @Override
    public List<Products> queryProducts(Products queryModel) {
        return productsDao.queryProducts(queryModel);
    }
}
