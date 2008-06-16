package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 20, 2008
 * Time: 7:30:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_PAGE_PRIVILEDGE")
public class PagePriviledge {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pagePriviledgeIdGenerator")
    @TableGenerator(name="pagePriviledgeIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_PAGE_PRIVILEDGE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;


    @OneToMany(targetEntity = PagePriviledgeValue.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name="PAGE_PRIVILEDGE_ID", referencedColumnName = "ID")
    private List<PagePriviledgeValue> values;

    @Version
    @Column(name = "VERSION")
    private int version;

}
