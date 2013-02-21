package ht.haapala.wss.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 *
 * @author Heikki Haapala
 */
public class AjaxLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.getWriter().print("logout_success");
            response.getWriter().flush();
        } else {
            super.onLogoutSuccess(request, response, authentication);
        }
    }
}
