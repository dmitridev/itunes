package org.astelit.itunes.auth.authorization;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;

public class AuthorizationContext {
    private static final ThreadLocal<org.astelit.itunes.auth.authorization.Authorization> Authorization = new ThreadLocal<>();

    @SneakyThrows
    public static <V> V doWithAuthorization(org.astelit.itunes.auth.authorization.Authorization authorization, Callable<V> runnable) {
        try {
            AuthorizationContext.Authorization.set(authorization);
            return runnable.call();
        } finally {
            AuthorizationContext.Authorization.set(null);
        }
    }

    public static org.astelit.itunes.auth.authorization.Authorization get() {
        return AuthorizationContext.Authorization.get();
    }

    public static <T extends org.astelit.itunes.auth.authorization.Authorization> T get(Class<T> target) {
        return (T) AuthorizationContext.Authorization.get();
    }
}
