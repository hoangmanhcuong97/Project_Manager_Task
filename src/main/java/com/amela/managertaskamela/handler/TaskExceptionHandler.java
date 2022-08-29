package com.amela.managertaskamela.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        System.out.println("Bug o day nay, vao fix di: " + e.getMessage());
        ModelAndView modelAndView = new ModelAndView("/error.404");
        return modelAndView;
    }
}
