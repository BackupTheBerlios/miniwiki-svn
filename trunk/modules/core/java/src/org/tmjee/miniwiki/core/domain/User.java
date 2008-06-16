package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 19, 2008
 * Time: 11:11:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_USER")
public class User {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userIdGenerator")
    @TableGenerator(name="userIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_USER_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    private long id;

    @Basic(optional = false)
    @Column(name="USERNAME", unique = true, nullable = false)
    private String username;

    @Basic
    @Column(name="PASSWORD")
    private String password;


    @OneToMany(targetEntity = UserProperty.class,
                cascade = {CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private List<UserProperty> userProperties;


    @ManyToMany(targetEntity = Group.class,
                fetch = FetchType.LAZY)
    @ElementForeignKey
    @JoinTable(name = "TBL_USER_GROUP",
                joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName = "ID")},
                inverseJoinColumns = {@JoinColumn(name="GROUP_ID", referencedColumnName = "ID")})
    private List<Group> groups;


    @Basic
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Basic
    @Column(name = "LAST_NAME")
    private String lastName;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;


    @Version
    @Column(name = "VERSION")
    private int version;
}
