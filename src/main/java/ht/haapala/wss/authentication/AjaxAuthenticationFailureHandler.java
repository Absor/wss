package ht.haapala.wss.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 *
 * @author Heikki Haapala
 */
public class AjaxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.getWriter().print("login_fail");
            response.getWriter().flush();
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
