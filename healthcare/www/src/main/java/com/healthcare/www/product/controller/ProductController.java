package com.healthcare.www.product.controller;

import com.healthcare.www.handler.FileHandler;
import com.healthcare.www.handler.FileType;
import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.domain.ProductTyped;
import com.healthcare.www.product.domain.SearchTyped;
import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.dto.ProductFileDTO;
import com.healthcare.www.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product/")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final FileHandler fh; // 파일핸들러(파일 저장 및 파일 객체 생성)

    // 상품등록 페이지로 이동하는 메서드
    @GetMapping("productManagement")
    public void getProductManagement(ProductDTO productDTO, Model model){
        model.addAttribute("productTyped", ProductTyped.values());
        model.addAttribute("searchTyped", SearchTyped.values());
    }

    // 상품등록 메서드(상품 및 첨부파일 등록)
    @PostMapping("productRegister")
    public String productRegister(@Valid ProductDTO productDTO, BindingResult bindingResult
            , @RequestParam(name = "files", required = false)MultipartFile[] files, RedirectAttributes re) {
        if(bindingResult.hasErrors()){ // 유효성 검증 에러 발생시
            re.addFlashAttribute("notValid", "상품정보를 입력하세요.");
            return "redirect:/product/productManagement";
        }
        log.info("첨부파일 개수 >>>>>> {}", files.length);
        // 첨부파일이 존재하면..
        if(files[0].getSize() > 0) {
            List<ProductFileDTO> productFileList = fh.uploadFile(files, FileType.PRODUCT);
            productDTO.setProductFileList(productFileList);
        }
        productService.addProduct(productDTO);
        return "redirect:/product/productManagement";
    }

    // 상품수정 메서드(상품 및 첨부파일 수정)
    @PostMapping("productModify")
    public String productModify(@Valid ProductDTO productDTO, BindingResult bindingResult
            , @RequestParam(name = "files", required = false)MultipartFile[] files, RedirectAttributes re) {
        if(bindingResult.hasErrors()){ // 유효성 검증 에러 발생시
            re.addFlashAttribute("notValid", "상품정보를 입력하세요.");
            return "redirect:/product/productManagement";
        }
        log.info("첨부파일 개수 >>>>>> {}", files.length);
        // 첨부파일이 존재하면..
        if(files[0].getSize() > 0) {
            List<ProductFileDTO> productFileList = fh.uploadFile(files, FileType.PRODUCT);
            productDTO.setProductFileList(productFileList);
        }
        productService.updateProduct(productDTO);
        return "redirect:/product/productManagement";
    }

    // 상품검색 메서드(관리자 상품 수정용)
    @GetMapping(value = "searchProduct" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> searchProductList
        (@RequestParam("category")String category, @RequestParam("keyword")String keyword){
        log.info("검색 하러 왔음");
        ProductDTO productDTO = ProductDTO.builder().
                category(category).
                keyword(keyword).
                build();
        List<Product> productList = productService.searchProductList(productDTO);
        return ResponseEntity.ok(productList);
    }



















}
