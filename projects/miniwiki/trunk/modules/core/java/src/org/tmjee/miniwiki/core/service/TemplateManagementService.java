package org.tmjee.miniwiki.core.service;

import org.tmjee.miniwiki.client.server.TemplateInfo;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.domain.UiWiki;
import org.tmjee.miniwiki.client.domain.UiWikis;
import org.tmjee.miniwiki.client.domain.UiSpace;
import org.tmjee.miniwiki.client.domain.UiSpacePriviledge;
import org.tmjee.miniwiki.core.domain.Wiki;
import org.tmjee.miniwiki.core.domain.Space;
import org.tmjee.miniwiki.core.domain.SpacePriviledge;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TemplateManagementService extends AbstractService {


    TemplateManagementService() {}
    public TemplateManagementService(JpaTemplate jpaTemplate, DozerBeanMapper mapper) {
        super(jpaTemplate, mapper);
    }


    public TemplateInfo loadTemplate(String wiki, String space, String page, String command) {
        if ((page != null) && (page.trim().length() > 0)) { // load this page
            // TODO:
            return new TemplateInfo("Page");
        }
        else if ((space != null) && (space.trim().length() > 0)) { // load this space
            // TODO:
            return new TemplateInfo("Space");
        }
        else if ((wiki != null) && (wiki.trim().length() > 0)) { // load this wiki
            // TODO:
            return new TemplateInfo("Wiki");
        }
        else { // inform them we can't load anything

            

            return new TemplateInfo("We can't load anything using this url / bookmark");
        }
    }


    public UiWikis getAllWikis(final PagingInfo pagingInfo) {
        return (UiWikis) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery("allWikis");
                query = preparePagingInfo(query, pagingInfo);
                return new UiWikis(
                        mapToList(query.getResultList(), UiWiki.class, "UiWiki"),
                        prepareResponsePagingInfo("count_allWikis", pagingInfo));
            }
        });
    }


    public UiWiki findWikiById(final long wikiId) {
        return (UiWiki) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery("getWikiById");
                query.setParameter("wikiId", wikiId);
                List<Wiki> _t = query.getResultList();
                if (_t.size() > 0) {
                    return map(_t.iterator().next(), UiWiki.class, "UiWiki");
                }
                return null;
            }
        });
    }


    public UiWiki findWikiByName(final String wikiName) {
        return (UiWiki) template.execute(new JpaCallback() {
            public Object doInJpa(EntityManager entityManager) throws PersistenceException {
                Query query = entityManager.createNamedQuery("getWikiByName");
                query.setParameter("wikiName", wikiName);
                List<Wiki> _t = query.getResultList();
                if (_t.size() > 0) {
                    return map(_t.iterator().next(), UiWiki.class, "UiWiki");
                }
                return null;
            }
        });
    }
}
