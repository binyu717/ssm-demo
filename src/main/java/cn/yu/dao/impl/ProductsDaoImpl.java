package cn.yu.dao.impl;

import cn.yu.dao.ProductsDao;
import cn.yu.dao.mapper.ProductsMapper;
import cn.yu.model.KeyAndValue;
import cn.yu.model.Product;
import cn.yu.model.ProductQuery;
import cn.yu.utils.response.PageInfo;
import com.google.common.base.CaseFormat;
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
    public Integer insertProduct(Product productsDo) {
        return productsMapper.insertProduct(productsDo);
    }

    @Override
    public PageInfo queryProducts(ProductQuery queryDo) {
        PageInfo pageInfo = new PageInfo();
        List<Product> products = productsMapper.queryProducts(queryDo);
        Long total = productsMapper.countProducts(queryDo);
        pageInfo.setList(products);
        pageInfo.setPageNo(queryDo.getPageNo());
        pageInfo.setPageSize(queryDo.getPageSize());
        pageInfo.setTotalNum(total);
        return pageInfo;
    }

    @Override
    public List<KeyAndValue> getCatologName() {
        return productsMapper.getCatologName();
    }

    public static void main(String[] args) {
        String userAdmin = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "userAdmin");
        System.out.println(userAdmin);
    }
}
