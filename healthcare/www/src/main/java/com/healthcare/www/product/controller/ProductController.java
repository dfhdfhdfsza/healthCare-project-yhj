package com.healthcare.www.product.controller;

import com.healthcare.www.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("productRegister")
    public void getProductRegister(){}
}
