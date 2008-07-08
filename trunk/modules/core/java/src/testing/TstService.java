package testing;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.core.domain.Wiki;
import org.tmjee.miniwiki.core.domain.WikiProperty;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.UserProperty;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 17, 2008
 * Time: 10:45:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class TstService {

    private JpaTemplate template;

    public TstService() {}

    public TstService(JpaTemplate template) {
        this.template = template;    
    }

    public void tryOut() {
        template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                User user = new User("u1", "u1", "u1", "u1");
                user.addProperty(new UserProperty("p1", "p1"));
                user.addProperty(new UserProperty("p2", "p2"));

                //entityManager.merge(user);
                entityManager.persist(user);

                return null;
            }
        });
        
    }

}
