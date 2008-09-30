package foo.bar.site.controller;

import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class DefaultFormController extends AbstractFormController {

    public static final String COMMAND_REQUEST_ID = "_COMMAND_";
    public static final String BINDING_RESULT_REQUEST_ID = "_BINDING_RESULT_";
        
    
    private String initialFormView;
    private String successFormView;
    private String failureFormView;

    public String getInitialFormView() {
        return initialFormView;
    }

    public void setInitialFormView(String initialFormView) {
        this.initialFormView = initialFormView;
    }

    public String getSuccessFormView() {
        return successFormView;
    }

    public void setSuccessFormView(String successFormView) {
        this.successFormView = successFormView;
    }

    public String getFailureFormView() {
        return failureFormView;
    }

    public void setFailureFormView(String failureFormView) {
        this.failureFormView = failureFormView;
    }

    protected ModelAndView showForm(final HttpServletRequest request, HttpServletResponse httpServletResponse, final BindException bindException) throws Exception {
        return new ModelAndView(initialFormView, new HashMap<String, Object>() {
            {
                put(getCommandName(), getCommand(request));
                put(BINDING_RESULT_REQUEST_ID, bindException);
            }
        });
    }


    

    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, final Object command, final BindException bindException) throws Exception {
        if (bindException.hasErrors()) {
            onFailure(request, response, command, bindException);
            request.setAttribute(BINDING_RESULT_REQUEST_ID, bindException.getBindingResult());
            request.setAttribute(COMMAND_REQUEST_ID, command);
            return new ModelAndView(failureFormView, new HashMap<String, Object>() {
                {
                    put(getCommandName(), command);
                    put(BINDING_RESULT_REQUEST_ID, bindException);
                }
            });
        } else {
            onSuccess(request, response, command, bindException);
            request.setAttribute(BINDING_RESULT_REQUEST_ID, bindException.getBindingResult());
            request.setAttribute(COMMAND_REQUEST_ID, command);
            return new ModelAndView(successFormView, new HashMap<String, Object>() {
                {
                    put(getCommandName(), command);
                    put(BINDING_RESULT_REQUEST_ID, bindException);
                }
            });
        }
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
    }
}
