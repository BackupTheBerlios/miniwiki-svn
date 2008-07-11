package testing;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.core.domain.Wiki;
import org.tmjee.miniwiki.core.domain.WikiProperty;
import org.tmjee.miniwiki.core.domain.User;
import org.tmjee.miniwiki.core.domain.UserProperty;
import org.tmjee.miniwiki.core.spring.OpenJpaCallback;
import org.tmjee.miniwiki.core.service.AbstractService;
import org.tmjee.miniwiki.client.domain.UiUser;
import org.tmjee.miniwiki.client.domain.UiUserProperty;
import org.apache.openjpa.persistence.OpenJPAEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.FlushModeType;
import java.util.ArrayList;
import java.util.List;

import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TstService extends AbstractService {

    TstService(){}
    public TstService(JpaTemplate template, DozerBeanMapper mapper) {
        super(template, mapper);
    }

    public void tryOut() {
        template.execute(new OpenJpaCallback<Void>() {
            public Void doInJpa(OpenJPAEntityManager entityManager) throws PersistenceException {

                /*Query query = entityManager.createQuery("select u from User as u left join fetch u.properties where u.id=71");
                User user = (User) query.getSingleResult();


                System.out.println("1] ========================================");
                System.out.println("username="+user.getUsername());
                System.out.println("firstname="+user.getFirstName());
                System.out.println("lastname="+user.getLastName());
                System.out.println("password="+user.getPassword());
                System.out.println("properties:-");
                for (UserProperty up: user.getProperties())  {
                    System.out.println("\t"+up.getClass()+"|"+up.getName()+"="+up.getValue());
                }




                System.out.println("2] ========================================");
                UiUser uiUser = map(user, new UiUser());
                uiUser.setFirstName(uiUser.getFirstName()+"x");
                uiUser.setLastName(uiUser.getLastName()+"x");
                uiUser.setDescription(uiUser.getDescription()+"x");
                uiUser.setPassword(uiUser.getPassword()+"x");
                uiUser.getProperties().get(0).setValue("zzzzz"+System.currentTimeMillis());
                uiUser.addProperty(new UiUserProperty("z1"+System.currentTimeMillis(), "z2"));
                System.out.println("username="+uiUser.getUsername());
                System.out.println("firstname="+uiUser.getFirstName());
                System.out.println("lastname="+uiUser.getLastName());
                System.out.println("password="+uiUser.getPassword());
                System.out.println("properties:-");
                for (UiUserProperty up: uiUser.getProperties())  {
                    System.out.println("\t"+up.getClass()+"|"+up.getName()+"="+up.getValue());
                }




                System.out.println("3] ========================================");
                map(uiUser,  user);
                System.out.println("username="+user.getUsername());
                System.out.println("firstname="+user.getFirstName());
                System.out.println("lastname="+user.getLastName());
                System.out.println("password="+user.getPassword());
                System.out.println("properties:-");
                for (UserProperty up: user.getProperties())  {
                    System.out.println("\t"+up.getClass()+"|"+up.getName()+"="+up.getValue());
                }*/


                User user = entityManager.find(User.class, 51);
                user.setId(51);
                user.setUsername("b");
                user.setFirstName("b");
                user.setLastName("b");
                user.setDescription("b");
                entityManager.merge(user);

                //entityManager.merge(user);

                return null;
            }
        });
        
    }

}
