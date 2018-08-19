package cn.yu.dao;

import cn.yu.model.KeyAndValue;
import cn.yu.model.Product;
import cn.yu.model.ProductQuery;
import cn.yu.utils.response.PageInfo;

import java.util.List;

/**
 * @author bin.yu
 * @create 2018-05-28 19:40
 **/
public interface ProductsDao {

    Integer insertProduct(Product productsDo);

    PageInfo queryProducts(ProductQuery queryDo);

    /**
     * 获取类别
     * @return
     */
    List<KeyAndValue> getCatologName();
}
