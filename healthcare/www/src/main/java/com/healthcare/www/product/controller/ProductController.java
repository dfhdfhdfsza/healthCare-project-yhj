package com.healthcare.www.product.controller;

import com.healthcare.www.handler.FileHandler;
import com.healthcare.www.product.ProductDTO;
import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.domain.ProductImageFile;
import com.healthcare.www.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final FileHandler fh;

    @GetMapping("productRegister")
    public void getProductRegister(){}
    @PostMapping("productRegister")
    public String productRegister(Product product, @RequestParam(name = "files", required = false)MultipartFile[] files) {
        List<ProductImageFile> productImageFileList = null;
        if(!files[0].isEmpty()){
            try {
                productImageFileList = fh.uploadFile(files);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct(product);
        productDTO.setProductImageFileList(productImageFileList);
        productService.save(productDTO);
        return "redirect:/";
    }
}
