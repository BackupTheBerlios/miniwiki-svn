package org.tmjee.miniwiki.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: May 20, 2008
 * Time: 6:44:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="TBL_SPACE_PRIVILEDGE")
public class SpacePriviledge {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "spacePriviledgeIdGenerator")
    @TableGenerator(name="spacePriviledgeIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "VALUE",
                    pkColumnValue = "TBL_SPACE_PRIVILEDGE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name="ID", unique = true, nullable = false)
    private long id;

    @OneToMany(targetEntity = SpacePriviledgeValue.class,
                cascade = {CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementJoinColumn(name = "SPACE_PRIVILEDGE_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private List<SpacePriviledgeValue> values;

    @Version
    @Column(name = "VERSION")
    private int version;
}
