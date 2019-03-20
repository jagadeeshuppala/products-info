package com.zensar.controller;

import com.zensar.model.Product;
import com.zensar.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api(value="Products - API", description="get the products list")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{categoryId}")
    public List<Product> getProductsList(@PathVariable("categoryId") String categoryId, @RequestParam(value="labelType",required = false) String priceLabelType){
        return service.getProductInfo(categoryId, priceLabelType );
    }
}
