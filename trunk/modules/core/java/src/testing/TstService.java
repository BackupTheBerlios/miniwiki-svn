package testing;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.core.domain.Wiki;
import org.tmjee.miniwiki.core.domain.WikiProperty;

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
                //entityManager.persist(new Wiki());

                //Wiki wiki = new Wiki();
                //wiki.addProperty(new WikiProperty("p1", "v1"));
                //wiki.addProperty(new WikiProperty("p2", "v2"));
                //wiki.addProperty(new WikiProperty("p3", "v3"));
                //entityManager.persist(wiki);


                //Query q = entityManager.createQuery("select w from Wiki as w left join fetch w.properties");
                //Wiki wiki = (Wiki) q.getSingleResult();
                //wiki.addProperty(new WikiProperty("1", "1"));
                //wiki.addProperty(new WikiProperty("2", "2"));
                //wiki.addProperty(new WikiProperty("3", "3"));
                //WikiProperty wp1 = wiki.getProperties().get(0);
                //WikiProperty wp2 = wiki.getProperties().get(1);
                //wiki.removeProperty(wp1);
                //wiki.removeProperty(wp2);

                //wiki.getProperties().remove(0);
                //wiki.getProperties().remove(1);
                //entityManager.
                //wiki.setProperties(null);

                //System.out.println(wiki.getProperties().getClass());
                //System.out.println("size(before)="+wiki.getProperties().size());



                 Query q = entityManager.createQuery("SELECT grp FROM Group as grp LEFT JOIN FETCH grp.users LEFT JOIN FETCH grp.properties");
                 System.out.println(q.getResultList().size());

                //wiki.getProperties().remove(0);

                //entityManager.merge(wiki);
                //entityManager.flush();


                //wiki.removeProperty(wp1);
                //wiki.removeProperty(wp2);
                //wiki.removeProperty(wp3);
                //System.out.println("size=(after)"+wiki.getProperties().size());
                //entityManager.persist(wiki);

                return null;
            }
        });
        
    }

}
