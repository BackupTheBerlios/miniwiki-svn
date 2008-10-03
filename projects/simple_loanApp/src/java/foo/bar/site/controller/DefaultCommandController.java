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

    public static final String BINDING_RESULT_REQUEST_ID = "_BINDING_RESULT_";
    

    private String failureCommandView;
    private String successCommandView;

    public String getFailureCommandView() {
        return failureCommandView;
    }

    public void setFailureCommandView(String failureCommandView) {
        this.failureCommandView = failureCommandView;
    }

    public String getSuccessCommandView() {
        return successCommandView;
    }

    public void setSuccessCommandView(String successCommandView) {
        this.successCommandView = successCommandView;
    }

    protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, final Object command, final BindException bindException) throws Exception {
        if (bindException.hasErrors()) {
            onFailure(request, response, command, bindException);
            return new ModelAndView(failureCommandView,
                new HashMap<String, Object>() {
                    {
                        put(getCommandName(), command);
                        put(BINDING_RESULT_REQUEST_ID, bindException);
                    }
                });
        }
        else {
            onSuccess(request, response, command, bindException);
            return new ModelAndView(successCommandView,
                new HashMap<String, Object>() {
                    {
                        put(getCommandName(), command);
                        put(BINDING_RESULT_REQUEST_ID, bindException);
                    }
                });
        }
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
    }
}
