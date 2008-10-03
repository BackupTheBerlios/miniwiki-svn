package foo.bar.site.interceptor;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import foo.bar.site.controller.UserAware;
import foo.bar.site.domain.User;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AccessInterceptor extends AbstractInterceptor {

    public static final String USER_SESSION_ID = "_USER_";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession(true);
        User accessSession = (User) session.getAttribute(USER_SESSION_ID);
        setAccessSessionIntoHandler(accessSession, o);
        if (accessSession == null) {
            performForwardOrRedirect(request, response);
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    protected void setAccessSessionIntoHandler(User accessSession, Object handler) {
        if (handler instanceof UserAware) {
            ((UserAware)handler).setUser(accessSession);
        }
    }
}
