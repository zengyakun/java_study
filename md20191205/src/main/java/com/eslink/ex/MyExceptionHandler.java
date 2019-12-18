package com.eslink.ex;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//
//@Log4j
//@Configuration
//public class MyExceptionHandler implements HandlerExceptionResolver {
//
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
//                                         Object handler, Exception ex) {
//        PrintWriter out = getPrintWrite(response);
//        if (ex instanceof XXXException) {
//            out.write(JsonUtil.formatJson(ResultEnum.PAY_ERROR.getCode(), ex.getMessage()));
//        } else {
//            out.write(JsonUtil.formatJson(ResultEnum.FAIL.getCode(), "服务器异常"));
//        }
//        if (null != out) {
//            out.close();
//        }
//        return mav;
//    }
//
//    private PrintWriter getPrintWrite(HttpServletResponse response) {
//        PrintWriter out = null;
//        try {
//            response.setHeader("Content-type", "text/html;charset=UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            out = response.getWriter();
//        } catch (IOException e) {
//            log.error("PrintWriter is exception", e);
//        }
//        return out;
//    }
//}
