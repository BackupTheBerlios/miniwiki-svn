package org.tmjee.miniwiki.domain;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 5:43:49 PM
 * To change this template use File | Settings | File Templates.
 */
@NamedQueries({
    @NamedQuery(
            name="isSetupBefore",
            query = "SELECT COUNT(wiki) FROM Wiki as wiki"
    ),
    @NamedQuery(
            name = "authenticate",
            query= "SELECT user FROM User as user WHERE user.name = :username AND user.password = :password "
    ),
    @NamedQuery(
            name = "getUserByUsername_full",
            query ="SELECT user FROM User as user JOIN FETCH user.groups JOIN FETCH user.userProperties WHERE user.name = :username"
    ),
    @NamedQuery(
            name="searchForUser_exact",
            query="SELECT user FROM User as user JOIN FETCH user.groups JOIN FETCH user.userProperties WHERE user.name = :username"
    ),
    @NamedQuery(
            name="searchForUser_non_exact",
            query="SELECT user FROM User as user"
    )
})
@Entity
@Table(name = "QUERIES")
public class Queries {
}
