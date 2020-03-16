package org.astelit.itunes.auth.secured;

import org.astelit.itunes.auth.authorization.Authorization;
import org.astelit.itunes.auth.authorization.AuthorizationContext;
import org.astelit.itunes.utils.ResponseUtil;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class SecuredInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authorization authorization = AuthorizationContext.get();

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Secured secured = AnnotationUtils.getAnnotation(method.getMethod(), Secured.class);

            if (secured == null)
                secured = AnnotationUtils.getAnnotation(method.getMethod().getDeclaringClass(), Secured.class);

            if (secured != null) {
                String[] value = secured.value();

                if (authorization == null) {
                    ResponseUtil.send(request, response, 403, "Доступ запрещен");
                    return false;
                }

                if (value.length > 0) {
                    boolean test = Arrays.stream(value).anyMatch(authorization.getActions()::contains);
                    if (!test) {
                        ResponseUtil.send(request, response, 403, "Доступ запрещен");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
