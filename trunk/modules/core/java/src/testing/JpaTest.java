package testing;

import org.springframework.context.ApplicationContext;
import org.tmjee.miniwiki.core.Bootstrap;

import javax.persistence.EntityManager;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class JpaTest {

    public static void main(String args[]) throws Exception {

        Bootstrap bootstrap = Bootstrap.getInstance();

        ApplicationContext ctx = bootstrap.getApplicationContext();
        TstService service = (TstService) ctx.getBean("tstService");

        service.tryOut();
        
    }


}
