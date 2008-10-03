import org.springframework.validation.DataBinder;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.MutablePropertyValues;
import foo.bar.site.controller.DeleteUserCommandControllerCommand;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 */
public class Testing {

    public static void main(String[] args) throws Exception {




        DeleteUserCommandControllerCommand command = new DeleteUserCommandControllerCommand();
        DataBinder binder = new DataBinder(command, "command");
        binder.bind(new MutablePropertyValues(
                new HashMap() {
                    {
                        put("userIds", new String[] {"1", "2", "3"});
                    }
                }));

        System.out.println(command.getUserIds());


    }
}
