package org.keycloak.example.spring.customer.interceptors;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wael.awada on 1/24/17.
 */

public class TokenCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        KeycloakAuthenticationToken authenticationToken = ((KeycloakAuthenticationToken) request.getUserPrincipal());

        if(authenticationToken != null) {
            System.out.println("authentication token is not null");
            AccessToken token = ((KeycloakPrincipal)(authenticationToken.getPrincipal())).getKeycloakSecurityContext().getToken();
            if (token.isExpired()) {
                System.out.println("AUTHENTICATION TOKEN IS EXPIRED");
                request.logout();
            }
        }

        return true;
    }
}
