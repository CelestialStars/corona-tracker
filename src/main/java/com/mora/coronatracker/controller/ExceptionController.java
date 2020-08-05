package com.mora.coronatracker.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller("error")
public class ExceptionController {
    private Log logger = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handelException(HttpServletRequest request, Exception ex){
        logger.error("Request: " + request.getRequestURL() + " raised " + ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex.getStackTrace());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}