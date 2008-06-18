package org.tmjee.miniwiki.core.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 21, 2008
 * Time: 4:21:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_SPACE_PROPERTY")
public class SpaceProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "spacePropertyIdGenerator")
    @TableGenerator(name="spacePropertyIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_SPACE_PROPERTY_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private String id;


    @Basic
    @Column(name="NAME", nullable = false)
    private String name;

    @Basic
    @Column(name="VALUE", nullable = false)
    private String value;


    @Version
    @Column(name = "VERSION")
    private int version;

}
