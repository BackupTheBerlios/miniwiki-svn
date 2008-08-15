package org.tmjee.miniwiki.core.service;

import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.tmjee.miniwiki.client.domain.UiIdentifiable;
import org.tmjee.miniwiki.client.Constants;
import org.tmjee.miniwiki.core.domain.Identifiable;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.*;

import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AbstractService {

    private static final Log LOG = LogFactory.getLog(AbstractService.class);

    protected JpaTemplate template;
    protected DozerBeanMapper mapper;

    protected AbstractService() {}
    public AbstractService(JpaTemplate template, DozerBeanMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    protected Query preparePagingInfo(Query query, PagingInfo pagingInfo) {
        int pageNumber = pagingInfo.getPageNumber()<=0 ? Constants.STARTING_PAGE_NUMBER : pagingInfo.getPageNumber();
        int pageSize = pagingInfo.getPageSize()<=0 ? Constants.DEFAULT_PAGE_SIZE : pagingInfo.getPageSize();
        int min = (pageNumber-1)*pagingInfo.getPageSize();
        int max = (pagingInfo.getPageNumber()*pagingInfo.getPageSize())-1;
        query.setFirstResult(min);
        query.setMaxResults(pageSize);
        return query;
    }

    protected ResponsePagingInfo prepareResponsePagingInfo(String namedQuery, PagingInfo pagingInfo) {
        return prepareResponsePagingInfo(namedQuery, Collections.EMPTY_MAP, pagingInfo);
    }

    protected ResponsePagingInfo prepareResponsePagingInfo(final String namedQuery, final Map<String, Object> params,
                                                           PagingInfo pagingInfo) {
        long totalRecords = (Long) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery(namedQuery);
                for (Map.Entry<String, Object> e : params.entrySet()) {
                    query.setParameter(e.getKey(), e.getValue());
                }
                return (Long) query.getSingleResult();
            }
        });
        return new ResponsePagingInfo(pagingInfo, totalRecords);
    }



    /*protected <F extends UiIdentifiable,T extends Identifiable> T mapFromUi(F f, Class<T> t, String context) {
        T ttt = (T) mapper.map(f, t, context);
        return ttt;
    }

    protected <F extends UiIdentifiable,T extends Identifiable> List<T> mapFromUiAsList(List<F> fs, Class<T> t, String context) {
        List<T> result = new ArrayList<T>();
        for (F f : fs) {
            result.add(mapFromUi(f, t, context));
        }
        return result;
    }

    protected <F extends UiIdentifiable, T extends Identifiable> Set<T> mapFromUiAsSet(EntityManager entityManager, List<F> fs, Class<T> t, String context) {
        Set<T> result = new LinkedHashSet<T>();
        for (F f: fs) {
            result.add(mapFromUi(f, t, context));
        }
        return result;
    }*/


    protected <F,T> T map(F f, Class<T> t, String context) {
        return (T) mapper.map(f, t, context);
    }

    protected <F,T> List<T> mapToList(List<F> fs, Class<T> t, String context) {
        List<T> result = new ArrayList<T>();
        for (F f : fs) {
            result.add(map(f, t, context));
        }
        return result;
    }

    protected <F,T> Set<T> mapToSet(List<F> fs, Class<T> t, String context) {
        Set<T> result = new LinkedHashSet<T>();
        for (F f : fs) {
            result.add(map(f, t, context));
        }
        return result;
    }






    protected void mergeOrPersist(EntityManager entityManager, Identifiable entity) {
        if (entity.getId() <= 0) {
            LOG.debug("Performing PERSIST on ["+entity+"]");
            entityManager.persist(entity);
        }
        else {
            LOG.debug("Performing MERGE on ["+entity+"]");
            entityManager.merge(entity);
        }
    }

}
