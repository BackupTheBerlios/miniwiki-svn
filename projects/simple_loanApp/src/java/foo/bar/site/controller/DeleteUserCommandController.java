package foo.bar.site.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foo.bar.site.service.Service;

import java.util.List;
import java.util.Iterator;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class DeleteUserCommandController extends DefaultCommandController {

    private Service service;

    public DeleteUserCommandController(Service service) {
        this.service = service;
    }

    protected void onFailure(HttpServletRequest request, HttpServletResponse response, Object _command, BindException bindException) {
        System.out.println("************* ON FAILURE");
        DeleteUserCommandControllerCommand command = (DeleteUserCommandControllerCommand) _command;
        for (Iterator i = bindException.getAllErrors().iterator(); i.hasNext(); ) {
            ObjectError e = (ObjectError) i.next();
            System.out.println(e.toString());
        }
        command.setUsers(service.getAllUsers());
    }

    protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Object _command, BindException bindException) {
        System.out.println("************** ON SUCCESS");
        DeleteUserCommandControllerCommand command = (DeleteUserCommandControllerCommand) _command;
        System.out.println("************* "+command.getUserIds());
        service.deleteUser(command.getUserIds());
        command.setUsers(service.getAllUsers());
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(List.class, new CustomCollectionEditor(List.class, true));
    }
}
