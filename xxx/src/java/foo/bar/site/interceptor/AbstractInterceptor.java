package foo.bar.site.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class AbstractInterceptor implements HandlerInterceptor {

    private boolean redirect = true;
    private String path = "";

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;    
    }

    public void setPath(String path) {
        this.path = path;
    }

    protected void performForwardOrRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!redirect) {
            request.getRequestDispatcher(path).forward(request, response);
        }
        else {
            response.sendRedirect(path);
        }
    }
}
