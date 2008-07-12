package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name="TBL_SPACE_PRIVILEDGE")
public class SpacePriviledge implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "spacePriviledgeIdGenerator")
    @TableGenerator(name="spacePriviledgeIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_SPACE_PRIVILEDGE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name="ID", unique = true, nullable = false)
    private long id;


    @Basic
    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(targetEntity = SpacePriviledgeValue.class,
                cascade = {CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementJoinColumn(name = "SPACE_PRIVILEDGE_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private Set<SpacePriviledgeValue> values = new LinkedHashSet<SpacePriviledgeValue>();

    @Version
    @Column(name = "VERSION")
    private int version;

    // === constructors ===
    public SpacePriviledge() {}


    // === getters ===

    public long getId() {
        return id;
    }

    public Set<SpacePriviledgeValue> getValues() {
        return values;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }



    // === setters ===

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValues(Set<SpacePriviledgeValue> values) {
        this.values = values;
    }

    public void setVersion(int version) {
        this.version = version;
    }



    

    // === equals & hashcode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof SpacePriviledge)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(name, ((SpacePriviledge)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
