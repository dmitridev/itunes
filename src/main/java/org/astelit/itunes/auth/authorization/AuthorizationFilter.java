package org.astelit.itunes.auth.authorization;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.astelit.itunes.exception.BadCredentialsException;
import org.astelit.itunes.utils.ResponseUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
@RequiredArgsConstructor
public class AuthorizationFilter extends GenericFilterBean {
    private final AuthorizationRegistry registry;
    private final AuthorizationHandler handler;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) request).getRequestURI();

        if (!registry.needAuthorization(requestUri)) {
            chain.doFilter(request, response);
            return;
        }

        Authorization authorization;

        try {
            authorization = handler.authorize((HttpServletRequest) request);
        } catch (BadCredentialsException ex) {
            log.error(ex.toString(), ex);
            ResponseUtil.send((HttpServletRequest) request, (HttpServletResponse) response, 401, ex.getMessage());
            return;
        }

        AuthorizationContext.doWithAuthorization(authorization, () -> {
            chain.doFilter(request, response);
            return null;
        });
    }
}
