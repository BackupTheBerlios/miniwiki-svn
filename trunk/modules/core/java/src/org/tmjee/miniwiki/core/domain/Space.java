package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.openjpa.persistence.jdbc.ForeignKey;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 19, 2008
 * Time: 11:06:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TBL_SPACE")
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "spaceIdGenerator")
    @TableGenerator(name="spaceIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_SPACE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name="ID", unique=true, nullable = false)
    private long id;

    @Basic(optional = false)
    @Column(name="NAME", unique = true, nullable = false)
    private String name;

    @OneToMany(targetEntity = Page.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY)
    @ElementJoinColumn(name = "SPACE_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private List<Page> pages;

    @ManyToOne(targetEntity = User.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY)
    @JoinColumn(name="CREATOR_ID", referencedColumnName = "ID")
    @ForeignKey
    private User creator;

    @OneToMany(targetEntity = SpacePriviledge.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY)
    @ElementJoinColumn(name = "SPACE_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private List<SpacePriviledge> pagePriviledges;

    @Version
    @Column(name = "VERSION")
    private int version;
   
}
