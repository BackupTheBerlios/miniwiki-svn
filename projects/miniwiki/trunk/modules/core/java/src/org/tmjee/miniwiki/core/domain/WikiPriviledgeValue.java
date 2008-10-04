package org.tmjee.miniwiki.core.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name="TBL_WIKI_PRIVILEDGE_VALUE")
public class WikiPriviledgeValue implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wikiPriviledgeValueIdGenerator")
    @TableGenerator(name="wikiPriviledgeValueIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_WIKI_PRIVILEDGE_VALUE_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name="ID", nullable = false)
    private long id;
    

    @Basic(fetch= FetchType.EAGER,
            optional=false)
    @Column(name="VALUE", nullable = false)
    private String value;

    @Version
    @Column(name = "VERSION")
    private int version;


    // === constructor ===
    public WikiPriviledgeValue() {}


    // === getters ===

    public long getId() {
        return id;
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

    public void setValue(String value) {
        this.value = value;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    

    // === equals & hashcode

    public boolean equals(Object obj) {
        if (!(obj instanceof WikiPriviledgeValue)) {
            return false;
        }
        if (this == obj) { return true; }
        return new EqualsBuilder()
                    .append(value, ((WikiPriviledgeValue)obj).getValue())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(value)
                    .toHashCode();
    }
}
