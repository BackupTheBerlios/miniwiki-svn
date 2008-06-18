package testing;

import org.springframework.context.ApplicationContext;
import org.tmjee.miniwiki.core.Bootstrap;

import javax.persistence.EntityManager;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 12, 2008
 * Time: 5:35:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class JpaTest {

    public static void main(String args[]) throws Exception {

        Bootstrap bootstrap = Bootstrap.getInstance();

        ApplicationContext ctx = bootstrap.getApplicationContext();
        TstService service = (TstService) ctx.getBean("tstService");

        service.tryOut();
        
    }


}
