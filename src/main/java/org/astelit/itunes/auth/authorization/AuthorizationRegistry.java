package org.astelit.itunes.auth.authorization;

import org.springframework.util.AntPathMatcher;

import java.util.LinkedHashMap;
import java.util.Map;


public class AuthorizationRegistry {
    private final Map<String, Boolean> matchers = new LinkedHashMap<>();
    private final AntPathMatcher matcher = new AntPathMatcher();

    public AuthorizationRegistry addRoute(String matcher, Boolean needAuthorization) {
        matchers.put(matcher, needAuthorization);
        return this;
    }

    public boolean needAuthorization(String requestUri) {
        for (String key : matchers.keySet()) {
            if (matcher.match(key, requestUri))
                return matchers.get(key);
        }

        return false;
    }
}
