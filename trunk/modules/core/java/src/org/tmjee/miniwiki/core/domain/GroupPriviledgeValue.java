package org.tmjee.miniwiki.core.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name="TBL_GROUP_PRIVILEDGE_VALUE")
public class GroupPriviledgeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groupPriviledgeValueIdGenerator")
    @TableGenerator(name="groupPriviledgeValueIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_GROUP_PRIVILEDGE_VALUE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name="VALUE")
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    // === equals & hashcode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof GroupPriviledgeValue)) {
            return false;
        }
        if (obj == this) { return true; }
        return new EqualsBuilder()
                    .append(value, ((GroupPriviledgeValue)obj).getValue())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(value)
                    .toHashCode();
    }
}
