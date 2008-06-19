package org.tmjee.miniwiki.core.service;

import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.server.ResponsePagingInfo;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Map;
import java.util.Collections;

import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 18, 2008
 * Time: 10:31:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractService {

    protected JpaTemplate template;
    protected DozerBeanMapper mapper;

    public AbstractService(JpaTemplate template, DozerBeanMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    protected Query preparePagingInfo(Query query, PagingInfo pagingInfo) {
        query.setFirstResult(pagingInfo.getPageNumber()*(pagingInfo.getPageSize()-1));
        query.setMaxResults(pagingInfo.getPageSize());
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

    protected <F,T> T map(F f, T t) {
        mapper.map(f, t);
        return t;
    }
}
