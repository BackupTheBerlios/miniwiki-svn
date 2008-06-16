package org.tmjee.miniwiki.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 20, 2008
 * Time: 9:51:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TBL_PAGE_PRIVILEDGE_VALUE")
public class PagePriviledgeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pagePriviledgeValueIdGenerator")
    @TableGenerator(name="pagePriviledgeValueIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_PAGE_PRIVILEDGE_VALUE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Basic
    @Column(name="VALUE")
    private String value;

    @Version
    @Column(name = "VERSION")
    private int version;
}
