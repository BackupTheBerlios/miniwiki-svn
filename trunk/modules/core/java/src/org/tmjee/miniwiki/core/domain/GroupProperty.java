package org.tmjee.miniwiki.core.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 20, 2008
 * Time: 11:01:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_GROUP_PROPERTY")
public class GroupProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groupPropertyIdGenerator")
    @TableGenerator(name="groupPropertyIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_GROUP_PROPERTY_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Basic
    @Column(name="NAME", nullable = false)
    private String name;

    @Basic
    @Column(name = "VALUE", nullable = false)
    private String value;

    @Version
    @Column(name = "VERSION")
    private int version;

}
