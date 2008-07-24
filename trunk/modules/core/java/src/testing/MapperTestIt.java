package testing;

import org.tmjee.miniwiki.core.Bootstrap;
import org.tmjee.miniwiki.core.service.SetupService;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.UserProperty;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiProperty;
import org.tmjee.miniwiki.client.domain.UiUserProperty;
import org.springframework.context.ApplicationContext;
import net.sf.dozer.util.mapping.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class MapperTestIt {

    public static void main(String[] args) throws Exception {

        Bootstrap bootstrap = Bootstrap.getInstance();

        ApplicationContext ctx = bootstrap.getApplicationContext();
        DozerBeanMapper mapper = (DozerBeanMapper) ctx.getBean("dozerBeanMapper");

        UiUser uiUser = new UiUser();
        uiUser.setUsername("u1");
        uiUser.setFirstName("u1");
        uiUser.setLastName("u1");
        uiUser.setDescription("u1");
        uiUser.setPassword("u1");
        uiUser.addProperty(new UiUserProperty("p1", "p1"));
        uiUser.addProperty(new UiUserProperty("p2", "p2"));
        uiUser.addGroup(new UiGroup());

        User user = new User();
        user.addProperty(new UserProperty("p2", "xxxxxxx"));
        mapper.map(uiUser, user);
        print(user);


        /*
        UiUser uiUser = new UiUser();
        User user = new User("uu1", "uu1", "uu1", "uu1");
        List<User> l1 = new ArrayList<User>();
        l1.add(user);
        List<UiUser> l2 = new ArrayList<UiUser>();
        mapper.map(l1, l2);

        System.out.println(l2.size());
        */
    }


    private static void print(User user) {
        System.out.println("username="+user.getUsername());
        System.out.println("firstname="+user.getFirstName());
        System.out.println("lastname="+user.getLastName());
        System.out.println("password="+user.getPassword());
        System.out.println("description="+user.getDescription());
        for (UserProperty up : user.getProperties()) {
            System.out.println("p->"+up.getName()+"="+up.getValue());
        }
    }
}
