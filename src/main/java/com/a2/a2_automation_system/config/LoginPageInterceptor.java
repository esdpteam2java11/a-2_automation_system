package com.a2.a2_automation_system.config;

import com.a2.a2_automation_system.user.Role;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@RequiredArgsConstructor
public class LoginPageInterceptor implements HandlerInterceptor {
    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("/login".equals(urlPathHelper.getLookupPathForRequest(request)) && isAuthenticated()) {
                String encodedRedirectURL = response.encodeRedirectURL(
                        request.getContextPath() + "/main");
                response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
                response.setHeader("Location", encodedRedirectURL);
                return false;
            }
        else if ("/default-page".equals(urlPathHelper.getLookupPathForRequest(request)) && isAuthenticated()) {
            String user = request.getRemoteUser();
            String path = String.format("/main",user);
            String encodedRedirectURL = response.encodeRedirectURL(
                    request.getContextPath() + path);
            response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
            response.setHeader("Location", encodedRedirectURL);
            return false;
        }

        else {
            return true;
        }
    }
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

}
