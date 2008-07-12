package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name = "TBL_USER")
public class User implements Identifiable {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userIdGenerator")
    @TableGenerator(name="userIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_USER_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    private long id;

    @Basic(optional = false)
    @Column(name="USERNAME", unique = true, nullable = false)
    private String username;

    @Basic
    @Column(name="PASSWORD")
    private String password;


    @OneToMany(targetEntity = UserProperty.class,
                cascade = {CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementForeignKey
    @ElementJoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private Set<UserProperty> properties = new LinkedHashSet<UserProperty>();


    @ManyToMany(targetEntity = Group.class,
                fetch = FetchType.LAZY)
    @ElementForeignKey
    @JoinTable(name = "TBL_USER_GROUP",
                joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName = "ID")},
                inverseJoinColumns = {@JoinColumn(name="GROUP_ID", referencedColumnName = "ID")})
    private Set<Group> groups = new LinkedHashSet<Group>();


    @Basic
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Basic
    @Column(name = "LAST_NAME")
    private String lastName;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;


    @Version
    @Column(name = "VERSION")
    private int version;


    // === constructor ===
    public User() {}
    public User(String username, String firstName, String lastName, String description) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public void addProperty(UserProperty userProperty) {
        properties.add(userProperty);
    }


    // ==== getters ===

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<UserProperty> getProperties() {
        return properties;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public int getVersion() {
        return version;
    }



    // === setters ===

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProperties(Set<UserProperty> properties) {
        this.properties = properties;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    
    // === toString ===

    public String toString() {
        return "id="+id+"\n"+
               "username="+username+"\n"+
               "firstName="+firstName+"\n"+
               "lastName="+lastName+"\n"+
               "password="+password+"\n"+ 
               "description="+description+"\n" +
               "properties="+properties+"\n" +
               "groups="+groups+"\n";
    }



    // === Equals & HashCode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(username, ((User)obj).getUsername())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(username)
                    .toHashCode();
    }
}
