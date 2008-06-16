package org.tmjee.miniwiki.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 21, 2008
 * Time: 4:20:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_WIKI_PROPERTY")
public class WikiProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wikiPropertyIdGenerator")
    @TableGenerator(name="wikiPropertyIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_WIKI_PROPERTY_CURRENT_ID", initialValue = 1,
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
}
