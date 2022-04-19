package com.teksystems.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@ControllerAdvice // Turns controller into exception handler
public class ErrorController {

    @RequestMapping(value = "/error/404")
    public String error404(HttpServletRequest request) {
        String originalUri = (String) request.getAttribute("javax.servlet.forward.request_uri");
        log.error("Requested URL not found : " + request.getMethod() + " " + originalUri);

        return "error/404";
    }

    @ExceptionHandler(Exception.class) // Handles all exceptions
    public ModelAndView handleAllExceptions(HttpServletRequest request, Exception e) {
        log.error("Error page exception: " + getRequestURL(request), e);
        ModelAndView model = new ModelAndView("error/500"); // when an exception occurs, page redirects to this file
        // shortcut for model.setViewName("error/500")

        String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(e));

        model.addObject("requestUrl", getRequestURL(request));
        model.addObject("message", e.getMessage());
        model.addObject("stackTrace", stackTrace);

        if ( e.getCause() != null ) {
            Throwable root = ExceptionUtils.getRootCause(e);
            model.addObject("rootcause", root);

            String roottrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(e));
            model.addObject("roottrace", roottrace);
        }


        return model;
    }

    private String getHTMLStackTrace(String[] stack) {
        StringBuffer result = new StringBuffer();

        for (String frame : stack) {
            if (frame.contains("teksystems")) {
                result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
            } else if (frame.contains("Caused by:")) {
                result.append("Caused by: " + frame + "<br>");
            }
        }

        return result.toString();
    }

    private String getRequestURL(HttpServletRequest request) {
        String result = request.getRequestURL().toString(); // Gets part of URL before ?
        if (request.getQueryString() != null) {
            result += "?" + request.getQueryString(); // gets part of URL after ? and we are adding it to the part before the ?
        }

        return result;
    }
}
