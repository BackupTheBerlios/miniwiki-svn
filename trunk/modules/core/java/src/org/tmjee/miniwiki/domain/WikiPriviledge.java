package org.tmjee.miniwiki.domain;

import org.apache.openjpa.persistence.PersistentCollection;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.openjpa.persistence.jdbc.ElementForeignKey;

import javax.persistence.*;
import java.util.List;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 19, 2008
 * Time: 2:18:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_WIKI_PRIVILEDGE")
public class WikiPriviledge {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wikiPriviledgeIdGenerator")
    @TableGenerator(name="wikiPriviledgeIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_WIKI_PRIVILEDGE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;


    @Column(name="NAME", unique = true, nullable = false)
    @Basic(fetch= FetchType.EAGER,
           optional=false)
    private String name;


    @OneToMany(targetEntity=WikiPriviledgeValue.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementJoinColumn(name="WIKI_PRIVILEDGE_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private List<WikiPriviledgeValue> values;

    @Version
    @Column(name = "VERSION")
    private int version;
}
