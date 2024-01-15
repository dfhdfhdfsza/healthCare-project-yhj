package com.healthcare.www.product.controller;

import com.healthcare.www.dto.ProductDTO;
import com.healthcare.www.dto.ProductFileDTO;
import com.healthcare.www.handler.FileHandler;
import com.healthcare.www.product.domain.Typed;
import com.healthcare.www.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product/")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final FileHandler fh; // 파일핸들러(파일 저장 및 파일 객체 생성)

    // 상품등록 페이지로 이동하는 메서드
    @GetMapping("productRegister")
    public void getProductRegister(ProductDTO productDTO, Model model){
        model.addAttribute("typed", Typed.values());
    }

    // 상품등록 메서드(상품 및 첨부파일 등록)
    @PostMapping("productRegister")
    public String productRegister(@Valid ProductDTO productDTO, BindingResult bindingResult
            , @RequestParam(name = "files", required = false)MultipartFile[] files, RedirectAttributes re) {
        if(bindingResult.hasErrors()){ // 유효성 검증 에러 발생시
            re.addFlashAttribute("notValid", "상품정보를 입력하세요.");
            return "redirect:/product/productRegister";
        }
        log.info("첨부파일 개수 >>>>>> {}", files.length);
        // 첨부파일이 존재하면..
        if(files[0].getSize() > 0) {
            List<ProductFileDTO> productFileList = fh.uploadFile(files);
            productDTO.setProductFileList(productFileList);
        }
        productService.addProduct(productDTO);
        return "redirect:/";
    }

}
