package foo.bar.site.controller;

import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class DefaultCommandController extends AbstractCommandController {

    public static final String COMMAND_REQUEST_ID = "_COMMAND_";
    public static final String BINDING_RESULT_REQUEST_ID = "_BINDING_RESULT_";
    

    private String viewName;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, final Object command, final BindException bindException) throws Exception {
        onHandle(request, response, command, bindException);
        request.setAttribute(COMMAND_REQUEST_ID, command);
        request.setAttribute(BINDING_RESULT_REQUEST_ID, bindException);
        return new ModelAndView(viewName, new HashMap<String, Object>() {
            {
                put(getCommandName(), command);
                put(BINDING_RESULT_REQUEST_ID, bindException);
            }
        });
    }

    protected void onHandle(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
        
    }
}
