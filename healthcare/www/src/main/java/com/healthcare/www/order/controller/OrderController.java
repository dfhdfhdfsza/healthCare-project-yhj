package com.healthcare.www.order.controller;

import com.healthcare.www.order.dto.OrderDTO;
import com.healthcare.www.product.domain.ProductTyped;
import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order/")
public class OrderController {

    private final ProductService productService;
    // 장바구니 페이지로 이동
    @GetMapping("cart")
    public void getCartPage(HttpSession session, Model model){
        List<OrderDTO> orderDTOList = (List<OrderDTO>) session.getAttribute("cartList");
        if (orderDTOList == null) {
            orderDTOList = new ArrayList<>();
        }
        model.addAttribute(orderDTOList);
    }
    @GetMapping("buy")
    public void getBuyPage(HttpSession session, Model model){
        List<OrderDTO> orderDTOList = (List<OrderDTO>) session.getAttribute("cartList");
        if (orderDTOList == null) {
            orderDTOList = new ArrayList<>();
        }
        model.addAttribute(orderDTOList);
    }
    // 장바구니 담기 기능 메서드
    @PostMapping("cart")
    public String addProductToCart(HttpSession session, OrderDTO orderDTO, Model model,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        // 세션에서 장바구니 목록 가져오기
        List<OrderDTO> orderDTOList = (List<OrderDTO>) session.getAttribute("cartList");
        // 해당 상품정보 가져오기
        ProductDTO productDTO = productService.getProduct(orderDTO.getProductNo());

        log.info("orderDTO >>> {}" , orderDTO);
        if (orderDTOList == null) {
            orderDTOList = new ArrayList<>();
        }
        
        orderDTO.setUserId(userDetails.getUsername());
        orderDTO.setProductDTO(productDTO);
        orderDTOList.add(orderDTO);
        session.setAttribute("cartList", orderDTOList);
        model.addAttribute("productTyped", ProductTyped.values());
        model.addAttribute("orderDTO", orderDTO);
        return "/order/cart";
    }

    // 구매하기 정보 메서드
    @PostMapping("payment")
    public String addProductToOrder(HttpSession session, OrderDTO orderDTO, Model model,
                                    @AuthenticationPrincipal UserDetails userDetails){
        ProductDTO productDTO = productService.getProduct(orderDTO.getProductNo());
        orderDTO.setUserId(userDetails.getUsername());
        orderDTO.setOrderNo(orderDTO.getUserId() + "_" + UUID.randomUUID());
        orderDTO.setProductDTO(productDTO);
        log.info("orderDTO >>> {}" , orderDTO);
        List<OrderDTO> orderDTOList = (List<OrderDTO>)session.getAttribute("cartList");
        if(orderDTOList == null){
            orderDTOList = new ArrayList<>();
        }
        orderDTOList.add(orderDTO);
        session.setAttribute("cartList", orderDTOList);
        model.addAttribute("productTyped", ProductTyped.values());
        model.addAttribute("orderDTO", orderDTO);
        return "/order/payment";
    }
}
