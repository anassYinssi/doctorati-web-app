package net.yinssi.doctorat_web_app.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin/home");
                return;
            } else if (authority.getAuthority().equals("ROLE_PROFESSOR")) {
                response.sendRedirect("/professor/home");
                return;
            } else if (authority.getAuthority().equals("ROLE_STUDENT")) {
                response.sendRedirect("/student/home");
                return;
            }
        }
        response.sendRedirect("/login?error"); // default fallback if no role matches
    }

}
