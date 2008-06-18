package org.tmjee.miniwiki.core.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 20, 2008
 * Time: 7:16:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TBL_SPACE_PRIVILEDGE_VALUE")
public class SpacePriviledgeValue {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "spacePriviledgeValueIdGenerator")
    @TableGenerator(name="spacePriviledgeValueIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_SPACE_PRIVILEDGE_VALUE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    private long id;

    @Basic
    @Column(name="VALUE")
    private String value;

    @Version
    @Column(name = "VERSION")
    private int version;
    
}

