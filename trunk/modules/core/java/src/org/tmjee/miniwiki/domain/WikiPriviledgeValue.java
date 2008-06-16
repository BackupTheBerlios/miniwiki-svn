package org.tmjee.miniwiki.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 19, 2008
 * Time: 3:09:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TBL_WIKI_PRIVILEDGE_VALUE")
public class WikiPriviledgeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wikiPriviledgeValueIdGenerator")
    @TableGenerator(name="wikiPriviledgeValueIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_WIKI_PRIVILEDGE_VALUE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name="ID", nullable = false, unique = true)
    private long id;
    

    @Basic(fetch= FetchType.EAGER,
            optional=false)
    @Column(name="VALUE", nullable = false)
    private String value;

    @Version
    @Column(name = "VERSION")
    private int version;

}
