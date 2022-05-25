package com.coursework.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException
    {
        String redirectUrl = request.getContextPath();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for(GrantedAuthority grantedAuthority: authorities)
        {
            if(grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
            {
                redirectUrl = "/admin/";
                break;
            }
            else if(grantedAuthority.getAuthority().equals("ROLE_MANAGER"))
            {
                redirectUrl = "/manager/";
                break;
            }
            else if(grantedAuthority.getAuthority().equals("ROLE_GUEST"))
            {
                redirectUrl = "/guest/";
                break;
            }
        }
        response.sendRedirect(redirectUrl);
    }
}
