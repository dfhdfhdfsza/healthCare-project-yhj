package com.healthcare.www.order.controller;

import com.healthcare.www.membership.service.MembershipService;
import com.healthcare.www.order.dto.OrderDTO;
import com.healthcare.www.order.dto.PaymentDTO;
import com.healthcare.www.order.service.OrderService;
import com.healthcare.www.order.service.PaymentService;
import com.healthcare.www.product.domain.ProductTyped;
import com.healthcare.www.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order/")
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final MembershipService membershipService;

    // 장바구니 페이지로 이동
    @GetMapping("cart")
    public void getCartPage(Model model, @AuthenticationPrincipal UserDetails userDetails){
        String userId = userDetails.getUsername(); // 로그인한 유저 아이디
        if(!userId.isEmpty()){
            int point = membershipService.getPoint(userId);
            List<OrderDTO> orderDTOList = orderService.findAll(userId);
            model.addAttribute("userPoint",point);
            model.addAttribute("orderDTOList", orderDTOList);
            model.addAttribute("productTyped", ProductTyped.values());
        }
    }

    // 장바구니 담기
    @PostMapping("cart")
    public String addProductToCart(OrderDTO orderDTO, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername(); // 로그인한 유저 아이디
        if(!userId.isEmpty()){
            int point = membershipService.getPoint(userId);
            orderDTO.setUserId(userId);
            List<OrderDTO> orderDTOList = orderService.addOrderList(orderDTO);
            model.addAttribute("userPoint",point);
            model.addAttribute("orderDTOList",orderDTOList);
            model.addAttribute("productTyped", ProductTyped.values());
        }
        return "redirect:/order/cart";
    }

    // 장바구니 상품 삭제
    @GetMapping("cart/delete/{orderNo}")
    public String deleteCart(@PathVariable String orderNo){
        orderService.deleteCart(orderNo);
        return "redirect:/order/cart";
    }

    // 장바구니 비우기
    @GetMapping("cart/clear")
    public String deleteCartAll(@AuthenticationPrincipal UserDetails userDetails){
        orderService.deleteCartAll(userDetails.getUsername());
        return "redirect:/order/cart";
    }

    // 장바구니 수량 및 결제금액 변경
    @PostMapping(value = "cart/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> editCart(@RequestBody OrderDTO orderDTO){
        int isOk = orderService.updateCart(orderDTO);
        return isOk > 0 ? ResponseEntity.ok("success") : ResponseEntity.badRequest().body("error");
    }

    // 주문 고개 정보 요청
    @GetMapping(value = "userInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(orderService.getUserInfo(userDetails.getUsername()));
    }

    // 주문 성공 시 DB 반영
    @GetMapping(value = "save/{userId}/{orderNumber}/{heldPoint}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> orderSave(@PathVariable String userId, @PathVariable String orderNumber,
                                            @PathVariable Integer heldPoint){
        log.info("포인트 >>>>>>> {} ", heldPoint);
        paymentService.orderSave(userId, orderNumber, heldPoint); // 주문정보 저장
        orderService.deleteCartAll(userId); // 장바구니 비우기
        return ResponseEntity.ok("주문정보 저장");
    }

    // 주문 내역 페이지
    @GetMapping("orderList")
    public void getOrderHistory(@AuthenticationPrincipal UserDetails userDetails, Model model){
        List<PaymentDTO> paymentDTOList = paymentService.getOrderHistory(userDetails.getUsername());
        paymentDTOList = paymentDTOList.stream().sorted(Comparator.comparing(PaymentDTO::getOrderDate).reversed()).toList(); // 날짜 내림차순 정렬
        model.addAttribute("orderList", paymentDTOList);
        model.addAttribute("productTyped", ProductTyped.values());
    }

    // 주문 취소
    @GetMapping("cancel/{orderNo}/{orderPoint}")
    public String orderCancel(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String orderNo, @PathVariable Integer orderPoint) {
        String userId = userDetails.getUsername();
        paymentService.orderCancel(orderNo, userId, orderPoint);
        return "redirect:/order/orderList";
    }

}
