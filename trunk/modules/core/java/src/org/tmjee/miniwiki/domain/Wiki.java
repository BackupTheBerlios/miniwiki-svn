package org.tmjee.miniwiki.domain;

import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.openjpa.persistence.jdbc.ElementForeignKey;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 19, 2008
 * Time: 12:44:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_WIKI")
public class Wiki {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wikiIdGenerator")
    @TableGenerator(name="wikiIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_WIKI_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;


    @OneToMany(targetEntity = WikiPriviledge.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY)
    @ElementJoinColumn(name="WIKI_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private List<WikiPriviledge> priviledges;


    @OneToMany(targetEntity = Space.class,
                cascade = {CascadeType.ALL},
                fetch=FetchType.LAZY)
    @ElementJoinColumn(name="WIKI_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private List<Space> spaces;


    @Version
    @Column(name = "VERSION")
    private int version;

    
}
