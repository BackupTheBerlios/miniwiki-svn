package org.tmjee.miniwiki.core.domain;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@NamedQueries({
    /* =========================
       === User / Groups / Auth
       ========================= */
    @NamedQuery(
            name="isSetupBefore",
            query = "SELECT setup FROM Setup as setup where setup.name='SETUP_DONE'"
    ),
    @NamedQuery(
            name = "authenticate",
            query= "SELECT user FROM User as user WHERE user.username = :username AND user.password = :password AND user.enabled=true"
    ),
    @NamedQuery(
            name = "getUserByUsername_full",
            query ="SELECT user FROM User as user LEFT JOIN FETCH user.properties LEFT JOIN FETCH user.groups WHERE user.username = :username"
    ),
    @NamedQuery(
            name="count_searchForUser_exact",
            query = "SELECT COUNT(user) FROM User as user WHERE user.username = :username"
    ),
    @NamedQuery(
            name="searchForUser_exact",
            query="SELECT user FROM User as user WHERE user.username = :username"
            /*query="SELECT user FROM User as user LEFT JOIN FETCH user.groups LEFT JOIN FETCH user.properties WHERE user.username = :username"*/
    ),
    @NamedQuery(
            name="count_searchForUser_not_exact",
            query="SELECT COUNT(user) FROM User as user WHERE LOWER(user.username) LIKE :username"
    ),
    @NamedQuery(
            name="searchForUser_not_exact",
            query="SELECT user FROM User as user WHERE LOWER(user.username) LIKE :username"
            /*query="SELECT user FROM User as user LEFT JOIN FETCH user.groups LEFT JOIN FETCH user.properties WHERE LOWER(user.username) LIKE :username"*/
    ),
    @NamedQuery(
            name="count_allUsers",
            query="SELECT COUNT(user) FROM User as user LEFT JOIN FETCH user.groups LEFT JOIN FETCH user.properties "
    ),
    @NamedQuery(
            name="allUsers",
            query="SELECT user FROM User as user"
            /*query="SELECT user FROM User as user LEFT JOIN FETCH user.groups LEFT JOIN FETCH user.properties"*/
    ),
    @NamedQuery(
            name="count_searchForGroup_exact",
            query="SELECT COUNT(grp) FROM Group as grp WHERE grp.name = :name"
    ),
    @NamedQuery(
            name="searchForGroup_exact",
            query="SELECT grp FROM Group as grp WHERE grp.name = :name"
            /*query="SELECT grp FROM Group as grp LEFT JOIN FETCH grp.users LEFT JOIN FETCH grp.properties WHERE grp.name = :name"*/
    ),
    @NamedQuery(
            name="count_searchForGroup_not_exact",
            query="SELECT COUNT(grp) FROM Group as grp WHERE LOWER(grp.name) LIKE :name"
    ),
    @NamedQuery(
            name="searchForGroup_not_exact",
            query="SELECT grp FROM Group as grp WHERE LOWER(grp.name) LIKE :name"
            /*query="SELECT grp FROM Group as grp LEFT JOIN FETCH grp.users LEFT JOIN FETCH grp.properties WHERE LOWER(grp.name) LIKE :name"*/
    ),
    @NamedQuery(
            name="count_allGroups",
            query="SELECT COUNT(grp) FROM Group as grp"
    ),
    @NamedQuery(
            name="allGroups",
            query="SELECT grp FROM Group as grp"
            /*query="SELECT grp FROM Group as grp LEFT JOIN FETCH grp.properties LEFT JOIN FETCH grp.users"*/
    ),

    // ======================================
    // === Wiki
    // ======================================
    @NamedQuery(
            name="allWikis",
            query="SELECT wiki FROM Wiki as wiki"
    ),
    @NamedQuery(
            name="count_allWikis",
            query="SELECT COUNT(wiki) FROM Wiki as wiki"
    ),
    @NamedQuery(
            name="getWikiById",
            query="SELECT wiki FROM Wiki as wiki LEFT JOIN FETCH wiki.priviledges LEFT JOIN FETCH wiki.spaces LEFT JOIN FETCH wiki.properties WHERE wiki.id=:wikiId"
    ),
    @NamedQuery(
            name="getWikiByName",
            query="SELECT wiki FROM Wiki as wiki LEFT JOIN FETCH wiki.priviledges LEFT JOIN FETCH wiki.spaces LEFT JOIN FETCH wiki.properties WHERE wiki.name=:wikiName"
    ),

        


    // ===================================
    // === Page
    // ===================================




    // =================================
    // === Access Management
    // =================================
    @NamedQuery(
            name="getGlobalPriviledges",
            query = "SELECT globalPriviledge FROM GlobalPriviledge as globalPriviledge LEFT JOIN FETCH globalPriviledge.values"
    ),
    @NamedQuery(
            name="findGlobalPriviledgeByName",
            query="SELECT globalPriviledge FROM GlobalPriviledge as globalPriviledge LEFT JOIN FETCH globalPriviledge.values WHERE globalPriviledge.name=:globalPriviledgeName"
    )

})
@Entity
@Table(name = "TBL_QUERIES")
public class Queries {
}
