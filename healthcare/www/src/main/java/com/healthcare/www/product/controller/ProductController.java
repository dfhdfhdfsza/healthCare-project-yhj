package com.healthcare.www.product.controller;

import com.healthcare.www.handler.FileHandler;
import com.healthcare.www.handler.FileType;
import com.healthcare.www.order.dto.OrderDTO;
import com.healthcare.www.product.domain.ProductTyped;
import com.healthcare.www.product.domain.SearchTyped;
import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.dto.ProductFileDTO;
import com.healthcare.www.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import static org.springframework.data.domain.Sort.Direction.DESC;

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
    public ResponseEntity<List<ProductDTO>> searchProductList
        (@RequestParam("category")String category, @RequestParam("keyword")String keyword){
        ProductDTO productDTO = ProductDTO.builder().
                category(category).
                keyword(keyword).
                build();
        List<ProductDTO> productDTOList = productService.searchProductList(productDTO);
        return ResponseEntity.ok(productDTOList);
    }

    // 상품목록 메소드
    @GetMapping("productList")
    public void getProductList(Model model, ProductDTO productDTO,
        @PageableDefault(size = 8 ,sort = "regDate", direction = DESC) Pageable pageable){
        log.info("파라미터 전달받음 productType >>>>> {}",productDTO.getProductType());
        // 페이지 기능을 담은 리스트 요청
        Page<ProductDTO> productDTOList = productService.getProductListAndPaging(productDTO, pageable);
//        List<ProductDTO> productDTOList = productService.getList();
        model.addAttribute("productDTOList", productDTOList);
        model.addAttribute("productTyped", ProductTyped.values());
    }

    // 상품 상세정보로 이동하는 메소드
    @GetMapping("productDetail")
    public void getProductDetail(Model model, OrderDTO orderDto, ProductDTO productDTO){
        productDTO = productService.getProduct(productDTO.getProductNo());
        model.addAttribute("productDTO", productDTO);
    }
















}
