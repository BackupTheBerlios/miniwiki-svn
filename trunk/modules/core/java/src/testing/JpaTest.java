package testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.tmjee.miniwiki.Bootstrap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

        Object o = ctx.getBean("entityManager");
        System.out.println(o instanceof EntityManager);
    }


}
