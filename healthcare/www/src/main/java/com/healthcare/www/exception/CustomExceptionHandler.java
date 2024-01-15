package com.healthcare.www.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, RedirectAttributes re){
        log.info("Exception Handler ERROR Message >>>>>>> {}", e.getMessage());
        re.addFlashAttribute("msg", e.getMessage());
        return "redirect:/error";
    }
}
