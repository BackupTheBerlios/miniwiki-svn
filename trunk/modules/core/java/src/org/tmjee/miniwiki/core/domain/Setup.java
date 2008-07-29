package org.tmjee.miniwiki.core.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name="TBL_SETUP")
public class Setup {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "setupIdGenerator")
    @TableGenerator(name="setupIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_SETUP_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name="ID", unique=true, nullable = false)
    private long id;

    @Basic
    @Column(name="NAME", unique=true, nullable=false)
    private String name;

    @Basic
    @Column(name="VALUE")
    private String value;


    public Setup(String name, String value) {
        this.name= name;
        this.value = value;
    }


    public void setValue(String value) {
        this.value = value;
    }



    // === getter ===
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }


    // === equals & hashcode ===
    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Setup)) {
            return false;
        }
        if (obj == this) { return true; }
        return new EqualsBuilder()
                    .append(name, ((Setup)obj).getName())
                    .isEquals();
    }
}
