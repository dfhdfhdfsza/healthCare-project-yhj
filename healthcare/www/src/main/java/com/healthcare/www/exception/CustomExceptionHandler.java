package com.healthcare.www.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    // 500 에러
    @ExceptionHandler(Exception.class)
    public String except(Exception ex, RedirectAttributes re) {
        Arrays.stream(ex.getStackTrace()).forEach(e ->log.info(e.toString()));
        re.addFlashAttribute("msg", "SERVER ERROR");
        return "redirect:/error";
    }
    // 400 에러
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String notFoundHandler(Exception ex, RedirectAttributes re){
        log.info("NotFoundException ERROR >>> {}", ex);
        re.addFlashAttribute("msg", "잘못된 요청");
        return "redirect:/error";
    }


}
