package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
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
@Table(name = "TBL_WIKI_PRIVILEDGE")
public class WikiPriviledge implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wikiPriviledgeIdGenerator")
    @TableGenerator(name="wikiPriviledgeIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_WIKI_PRIVILEDGE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;


    @Column(name="NAME", unique = true, nullable = false)
    @Basic(fetch= FetchType.EAGER,
           optional=false)
    private String name;


    @OneToMany(targetEntity=WikiPriviledgeValue.class,
                cascade={CascadeType.ALL},
                fetch=FetchType.EAGER)
    @ElementJoinColumn(name="WIKI_PRIVILEDGE_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private Set<WikiPriviledgeValue> values = new LinkedHashSet<WikiPriviledgeValue>();

    @Version
    @Column(name = "VERSION")
    private int version;


    // === getters ===

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<WikiPriviledgeValue> getValues() {
        return values;
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

    public void setValues(Set<WikiPriviledgeValue> values) {
        this.values = values;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    

    // === equals & hashcode

    public boolean equals(Object obj) {
        if (!(obj instanceof WikiPriviledge)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(name, ((WikiPriviledge)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
