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
@Table(name = "TBL_GROUP")
public class Group implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groupIdGenerator")
    @TableGenerator(name="groupIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_GROUP_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Basic
    @Column(name="NAME", nullable = false)
    private String name;

    @ManyToMany(targetEntity = User.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY,
                mappedBy = "groups")
    @OrderBy("username ASC")
    private Set<User> users = new LinkedHashSet<User>();

    @OneToMany(targetEntity = GroupProperty.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    @OrderBy("name ASC")
    private Set<GroupProperty> properties = new LinkedHashSet<GroupProperty>();

    @Version
    @Column(name = "VERSION")
    private int version;

    @Basic
    @Column(name="DESCRIPTION")
    private String description;



    // === constructors ===
    public Group() {
    }



    // === misc ===
    public void addProperty(GroupProperty property) {
        properties.add(property);
    }


    // === getters ===

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<GroupProperty> getProperties() {
        return properties;
    }

    public int getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }


    // === setters ===
    
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setProperties(Set<GroupProperty> properties) {
        this.properties = properties;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // === toString ===

    public String toString() {
         return "id="+id+"\n"+
                "description="+description+"\n"+
                "properties="+properties+"\n";
    }
    // === equals & hashcode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof Group)) {
            return false;
        }
        if (obj == this) { return true; }
        return new EqualsBuilder()
                    .append(name, ((Group)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
