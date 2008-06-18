package org.tmjee.miniwiki.core.domain;

import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;
import org.apache.openjpa.persistence.jdbc.ElementForeignKey;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 19, 2008
 * Time: 12:44:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_WIKI")
public class Wiki {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wikiIdGenerator")
    @TableGenerator(name="wikiIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_WIKI_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;


    @OneToMany(targetEntity = WikiPriviledge.class,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY)
    @ElementJoinColumn(name="WIKI_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private List<WikiPriviledge> priviledges;


    @OneToMany(targetEntity = Space.class,
                cascade = {CascadeType.ALL},
                fetch=FetchType.LAZY)
    @ElementJoinColumn(name="WIKI_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private List<Space> spaces;


    @OneToMany(targetEntity = WikiProperty.class,
               cascade = {CascadeType.ALL},
               fetch = FetchType.EAGER)
    @ElementJoinColumn(name = "WIKI_ID", referencedColumnName = "ID")
    @ElementForeignKey
    private List<WikiProperty> properties;



    @Version
    @Column(name = "VERSION")
    private int version;





    public Wiki() {
        priviledges = new ArrayList<WikiPriviledge>();
        spaces = new ArrayList<Space>();
        properties = new ArrayList<WikiProperty>();
    }


    public void addPriviledge(WikiPriviledge priviledge) {
        priviledges.add(priviledge);
    }
    public void removePriviledge(WikiPriviledge priviledge) {
        priviledges.remove(priviledge);
    }


    public void addSpace(Space space) {
        spaces.add(space);
    }
    public void removeSpace(Space space) {
        spaces.remove(space);
    }



    public List<WikiProperty> getProperties() {
        return properties;
    }
    public void setProperties(List<WikiProperty> properties) {
        this.properties = properties;
    }
    public void addProperty(WikiProperty property) {
        properties.add(property);
    }
    public void removeProperty(WikiProperty property) {
        properties.remove(property);
    }

    
}
