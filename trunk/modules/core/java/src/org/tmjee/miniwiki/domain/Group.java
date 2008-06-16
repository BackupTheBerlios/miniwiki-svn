package org.tmjee.miniwiki.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 20, 2008
 * Time: 10:43:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_GROUP")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groupIdGenerator")
    @TableGenerator(name="groupIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_GROUP_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @ManyToMany(targetEntity = User.class,
                fetch = FetchType.LAZY,
                mappedBy = "groups")
    private List<User> users;

    @OneToMany(targetEntity = GroupProperty.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    private List<GroupProperty> groupInfo;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Basic
    @Column(name="DESCRIPTION")
    private String description;

}
