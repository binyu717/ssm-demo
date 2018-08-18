package cn.yu.controller;

import cn.yu.model.ProductQuery;
import cn.yu.services.ProductsService;
import cn.yu.utils.response.PageInfo;
import cn.yu.utils.response.ResponseInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bin.yu
 * @create 2018-05-28 19:51
 **/
@Controller
@RequestMapping("products")
public class ProductsController {


    @Autowired(required=false)
    private ProductsService productsService;

    @ResponseBody
    @RequestMapping("queryProduct")
    public ResponseInfo queryProduct(ProductQuery queryModel) {
        try {
            PageInfo pageInfo = productsService.queryProducts(queryModel);
            return ResponseInfo.success(pageInfo);
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error("查询异常：{}" + e);
            return ResponseInfo.error(e + e.getMessage());
        }
    }
}
