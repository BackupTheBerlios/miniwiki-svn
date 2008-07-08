package org.tmjee.miniwiki.core.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 20, 2008
 * Time: 10:43:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_USER_PROPERTY")
public class UserProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userPropertyIdGenerator")
    @TableGenerator(name="userPropertyIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_USER_PROPERTY_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;


    @Basic
    @Column(name="NAME", nullable = false)
    private String name;

    @Basic
    @Column(name="VALUE", nullable = false)
    private String value;


    @Version
    @Column(name = "VERSION")
    private int version;

    public UserProperty() {}
    public UserProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
