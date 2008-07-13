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

                


                return null;
            }
        });
        
    }

}
