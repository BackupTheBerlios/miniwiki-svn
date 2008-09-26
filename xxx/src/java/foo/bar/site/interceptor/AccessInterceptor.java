package foo.bar.site.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import foo.bar.site.domain.AccessSession;
import foo.bar.site.controller.AccessSessionAware;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AccessInterceptor extends AbstractInterceptor {

    public static final String ACCESS_SESSION_ID = "_ACCESS_";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession(true);
        AccessSession accessSession = (AccessSession) session.getAttribute(ACCESS_SESSION_ID);
        setAccessSessionIntoHandler(accessSession, o);
        performForwardOrRedirect(request, response);
        if (accessSession == null) {
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    protected void setAccessSessionIntoHandler(AccessSession accessSession, Object handler) {
        if (handler instanceof AccessSessionAware) {
            ((AccessSessionAware)handler).setAccessSession(accessSession);
        }
    }
}
