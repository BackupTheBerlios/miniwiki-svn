package testing;

import org.tmjee.miniwiki.core.Bootstrap;
import org.tmjee.miniwiki.core.service.UserManagementService;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiUserProperty;
import org.springframework.context.ApplicationContext;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class UserManagementServiceTestIt {

    public static void main(String[] args) throws Exception {

        Bootstrap bootstrap = Bootstrap.getInstance();

        ApplicationContext ctx = bootstrap.getApplicationContext();
        UserManagementService service = (UserManagementService) ctx.getBean("userManagementService");

        UiUser uiUser = new UiUser("c", "c", ""+System.currentTimeMillis(), "cc");
        uiUser.setId(141);
        uiUser.addProperty(new UiUserProperty("p1"+ System.currentTimeMillis(), "p1v"+System.currentTimeMillis()));
        //uiUser.addProperty(new UiUserProperty("p2", "p2v"));

        service.updateUser(uiUser);





    }
}
