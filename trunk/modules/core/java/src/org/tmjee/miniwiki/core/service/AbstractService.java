package org.tmjee.miniwiki.core.service;

import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.tmjee.miniwiki.client.domain.UiIdentifiable;
import org.tmjee.miniwiki.core.domain.Identifiable;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;

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

    protected JpaTemplate template;
    protected DozerBeanMapper mapper;

    protected AbstractService() {}
    public AbstractService(JpaTemplate template, DozerBeanMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    protected Query preparePagingInfo(Query query, PagingInfo pagingInfo) {
        query.setFirstResult((pagingInfo.getPageNumber()-1)*pagingInfo.getPageSize());
        query.setMaxResults((pagingInfo.getPageNumber()*pagingInfo.getPageSize())-1);
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

    /*protected <F,T> List<T> mapList(List<F> fs, List<T> ts, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        for (F f: fs) {
            result.add(map(f, clazz));
        }
        return result;
    }

    protected <F,T> T map(F f, T t) {
        mapper.map(f, t);
        return t;
    }

    protected <F,T> T map(F f, Class<T> tclass) {
        return (T) mapper.map(f, tclass);
    }*/








    protected <F extends UiIdentifiable,T extends Identifiable> T mapFromUi(EntityManager entityManager, F f, Class<T> t) {
        T _t = entityManager.find(t, f.getId());
        if (_t != null) {
            mapper.map(f, _t);
            return _t;
        }
        return (T) mapper.map(f, t);
    }

    protected <F extends UiIdentifiable,T extends Identifiable> List<T> mapFromUiAsListToList(EntityManager entityManager, List<F> fs, Class<T> t) {
        List<T> result = new ArrayList<T>();
        for (F f : fs) {
            result.add(mapFromUi(entityManager, f, t));
        }
        return result;
    }

    protected <F extends UiIdentifiable, T extends Identifiable> Set<T> mapFromUiAsListToSet(EntityManager entityManager, List<F> fs, Class<T> t) {
        Set<T> result = new LinkedHashSet<T>();
        for (F f: fs) {
            result.add(mapFromUi(entityManager, f, t));
        }
        return result;
    }


    protected <F extends Identifiable,T extends UiIdentifiable> T mapFromEntity(F f, Class<T> t) {
        return (T) mapper.map(f, t);
    }

    protected <F extends Identifiable,T extends UiIdentifiable> List<T> mapFromEntityToList(List<F> fs, Class<T> t) {
        List<T> result = new ArrayList<T>();
        for (F f : fs) {
            result.add(mapFromEntity(f, t));
        }
        return result;
    }

    protected <F extends Identifiable,T extends UiIdentifiable> Set<T> mapFromEntityToSet(List<F> fs, Class<T> t) {
        Set<T> result = new LinkedHashSet<T>();
        for (F f : fs) {
            result.add(mapFromEntity(f, t));
        }
        return result;
    }



}
