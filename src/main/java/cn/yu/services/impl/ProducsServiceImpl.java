package cn.yu.services.impl;

import cn.yu.dao.ProductsDao;
import cn.yu.model.KeyAndValue;
import cn.yu.model.Product;
import cn.yu.model.ProductQuery;
import cn.yu.services.ProductsService;
import cn.yu.utils.response.PageInfo;
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
    public Integer insertProduct(Product productsModel) {
        return productsDao.insertProduct(productsModel);
    }

    @Override
    public PageInfo queryProducts(ProductQuery queryModel) {
        return productsDao.queryProducts(queryModel);
    }

    @Override
    public List<KeyAndValue> getCatologName() {
        return productsDao.getCatologName();
    }
}
