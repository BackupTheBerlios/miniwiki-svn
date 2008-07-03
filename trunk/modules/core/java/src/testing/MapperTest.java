package testing;

import org.tmjee.miniwiki.core.Bootstrap;
import org.tmjee.miniwiki.core.service.SetupService;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiGroup;
import org.tmjee.miniwiki.client.domain.UiProperty;
import org.tmjee.miniwiki.client.domain.UiUserProperty;
import org.springframework.context.ApplicationContext;
import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jul 3, 2008
 * Time: 4:38:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapperTest {

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
        uiUser.addGroup(new UiGroup());
        uiUser.addGroup(new UiGroup());
        uiUser.addProperty(new UiUserProperty());

        User user = new User();
        mapper.map(uiUser, user);

        System.out.println(user);





    }
}
