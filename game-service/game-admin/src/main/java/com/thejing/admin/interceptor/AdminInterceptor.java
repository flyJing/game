package com.thejing.admin.interceptor;

import com.thejing.model.common.pojos.GameAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * admin自定义拦截器
 */
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        HttpSession session = request.getSession();
//        GameAdmin gameAdmin = (GameAdmin) session.getAttribute("user");
//        if (gameAdmin == null ){
//            response.sendError(401,"请先登录!");
//            return false;
//        }
        log.info("preHandle被执行了");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandel被执行了");
    }
}
