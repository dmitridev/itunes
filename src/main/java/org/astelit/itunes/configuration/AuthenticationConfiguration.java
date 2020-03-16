package org.astelit.itunes.configuration;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.Getter;
import org.astelit.itunes.auth.authorization.Authorization;
import org.astelit.itunes.auth.authorization.AuthorizationHandler;
import org.astelit.itunes.auth.authorization.AuthorizationRegistry;
import org.astelit.itunes.exception.BadCredentialsException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Configuration
public class AuthenticationConfiguration {

    @Bean
    public AuthorizationRegistry authorizationRegistry() {
        return new AuthorizationRegistry()
                .addRoute("/api/**", true);
    }

    @Bean
    public AuthorizationHandler authorizationHandler(AuthenticationProperties properties) {
        return request -> {
            String header = request.getHeader("Authorization");
            String token = null;

            if (header != null && header.startsWith("Bearer "))
                token = header.substring(7);

            if (token == null)
                token = request.getParameter("token");

            if (StringUtils.isEmpty(token))
                throw new BadCredentialsException("Missing token");

            DefaultClaims claims;

            try {
                claims = (DefaultClaims) Jwts.parser()
                        .setSigningKey(properties.getSalt())
                        .parse(token)
                        .getBody();
            } catch (ExpiredJwtException ex) {
                throw new BadCredentialsException("Token expired");
            } catch (MalformedJwtException | SignatureException ex) {
                throw new BadCredentialsException("Invalid token");
            }

            return new DefaultAuthorization(claims);
        };
    }

    @Getter
    public static class DefaultAuthorization implements Authorization {
        private final long id = -1L;
        private final String login;
        private final Collection<String> actions;
        private final Collection<String> roles = null;
        private final Collection<String> groups = null;

        @SuppressWarnings("unchecked")
        DefaultAuthorization(DefaultClaims claims) {
            this.login = claims.getSubject();
            this.actions = (Collection<String>) claims.get("actions");
        }
    }
}
