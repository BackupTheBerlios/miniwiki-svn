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
 * Time: 11:07:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_PAGE")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pageIdGenerator")
    @TableGenerator(name="pageIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_PAGE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Basic(optional = false)
    @Column(name="NAME", nullable = false)
    private String name;

    @OneToMany(targetEntity = PagePriviledge.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.LAZY)
    @ElementForeignKey
    @ElementJoinColumn(name="PAGE_ID", referencedColumnName = "ID")
    private List<PagePriviledge> priviledges;

    @ManyToOne(targetEntity = Space.class, fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="SPACE_ID", referencedColumnName = "ID")
    private Space space;

    @OneToMany(targetEntity = Attachment.class,
                cascade = {CascadeType.ALL},
                fetch=FetchType.LAZY)
    @ElementForeignKey
    @ElementJoinColumn(name = "PAGE_ID", referencedColumnName = "ID")
    private List<Attachment> attachments;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, optional = false)
    @ForeignKey
    @JoinColumn(name="CREATOR_ID", referencedColumnName = "ID")
    private User creator;


    @ManyToOne(targetEntity = User.class,
                fetch = FetchType.LAZY)
    @JoinColumn(name = "LAST_MODIFIED_USER_ID", referencedColumnName = "ID")
    @ForeignKey
    private User lastModifiedUser;


    @OneToMany(targetEntity = PreviousVersion.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.LAZY)
    @ElementForeignKey
    @ElementJoinColumn(name = "PAGE_ID", referencedColumnName = "ID")
    private List<PreviousVersion> perviousVersions;


    @OneToMany(targetEntity = PageProperty.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.LAZY)
    @ElementForeignKey
    @ElementJoinColumn(name = "PAGE_ID", referencedColumnName = "ID")
    private List<PageProperty> properties;

    
    @OneToMany(targetEntity = Page.class,
                fetch = FetchType.LAZY,
                mappedBy = "parent")
    private List<Page> children;


    @ManyToOne(targetEntity = Page.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_PAGE_ID", referencedColumnName = "ID")
    @ForeignKey
    private Page parent;
   

    @Version
    @Column(name = "VERSION")
    private int version;
}
