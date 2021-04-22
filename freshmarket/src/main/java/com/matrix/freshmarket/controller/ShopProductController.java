package com.matrix.freshmarket.controller;

import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class ShopProductController {


    private ProductService productService;

    @Autowired
    public ShopProductController(ProductService productService) {
        this.productService = productService;
    }





    @GetMapping("/shop{name}")
    public String shopPage(@PathVariable("name") String name,
                           @RequestParam (value = "page",
                                              required=false ,
                                              defaultValue = "0")
                                              Integer page,
                                              Model model) {

       Page<ProductEntity> productPage =
                productService.getProduct(PageRequest.of(page,8));
        model.addAttribute("products",productPage);
        model.addAttribute("numbers", IntStream.range(0,productPage.getTotalPages()).toArray());

        return "FreshMarketShop-1";
    }




    @GetMapping("/shop/Food")
    public String shopPageFood(@RequestParam (value = "page",
            required=false ,
            defaultValue = "0")
                                   Integer page,
                           Model model) {

        Page<ProductEntity> productPage =
                productService.getFood(PageRequest.of(page,8));
        model.addAttribute("products",productPage);
        model.addAttribute("numbers", IntStream.range(0,productPage.getTotalPages()).toArray());

        return "FreshMarketShop-1";
    }

}
