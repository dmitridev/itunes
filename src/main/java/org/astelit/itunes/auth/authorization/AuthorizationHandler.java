package org.astelit.itunes.auth.authorization;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationHandler {
    Authorization authorize(HttpServletRequest request);
}
