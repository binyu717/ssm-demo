package cn.yu.controller;

import cn.yu.model.Products;
import cn.yu.services.ProductsService;
import cn.yu.utils.response.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public ResponseInfo queryProduct(Products queryModel) {
        List<Products> productsModels = productsService.queryProducts(queryModel);
        return ResponseInfo.success(productsModels);
    }
}
