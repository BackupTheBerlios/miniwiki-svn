package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name="TBL_GROUP_PRIVILEDGE")
public class GroupPriviledge {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groupPriviledgeIdGenerator")
    @TableGenerator(name="groupPriviledgeIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_GROUP_PRIVILEDGE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name="NAME", nullable = false)
    private String name;

    @OneToMany(targetEntity = GroupPriviledgeValue.class,
                cascade={CascadeType.ALL},
                fetch = FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name = "GROUP_PRIVILEDGE_ID", referencedColumnName = "ID")
    private Set<GroupPriviledgeValue> values = new LinkedHashSet<GroupPriviledgeValue>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GroupPriviledgeValue> getValues() {
        return values;
    }

    public void setValues(Set<GroupPriviledgeValue> values) {
        this.values = values;
    }


    // === equals & hashcode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof GroupPriviledge)) {
            return false;
        }
        if (obj == this) { return true; }
        return new EqualsBuilder()
                    .append(name, ((GroupPriviledge)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
