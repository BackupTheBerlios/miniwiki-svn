package org.tmjee.miniwiki.core.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name = "TBL_USER_PROPERTY")
public class UserProperty implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userPropertyIdGenerator")
    @TableGenerator(name="userPropertyIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_USER_PROPERTY_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", nullable = false)
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


    // === constructor ===
    public UserProperty() {}
    public UserProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // === getters ===

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getVersion() {
        return version;
    }


    // === setters ===
    public void setId(long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    // === toString ===
    public String toString() {
        return "id="+id+"\n"+
               "name="+name+"\n"+
               "value="+value;
    }

    

    // == equals & hashcode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof UserProperty)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(name, ((UserProperty)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
