package org.tmjee.miniwiki.core.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name="TBL_GLOBAL_PRIVILEDGE_VALUE")
public class GlobalPriviledgeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "globalPriviledgeValueIdGenerator")
    @TableGenerator(name="globalPriviledgeValueIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_GLOBAL_PRIVILEDGE_VALUE", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name = "VALUE")
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


    public GlobalPriviledgeValue() {}
    public GlobalPriviledgeValue(String value) {
        this.value = value;
    }


    // === equals & hashcode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof GlobalPriviledgeValue)) {
            return false;
        }
        if (obj == this) { return true; }
        return new EqualsBuilder()
                    .append(value, ((GlobalPriviledgeValue)obj).getValue())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(value)
                    .toHashCode();
    }
}
