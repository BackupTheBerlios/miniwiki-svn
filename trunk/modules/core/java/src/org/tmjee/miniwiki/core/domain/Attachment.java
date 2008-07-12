package org.tmjee.miniwiki.core.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.*;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
@Entity
@Table(name = "TBL_ATTACHEMENT")
public class Attachment implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "attachmentIdGenerator")
    @TableGenerator(name="attachmentIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_ATTACHMENT_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Basic
    @Column(name="NAME", nullable=false)
    private String name;

    @Basic(optional = false)
    @Column(name = "CONTENT_TYPE", nullable = false)
    private String contentType;

    @Lob
    @Column(name = "ATTACHMENT", nullable = false)
    private byte[] attachment;

    @Version
    @Column(name = "VERSION")
    private int version;


    // === constructors ===
    public Attachment() {}


    // === getters ===
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }


    // === setters ===

    public String getContentType() {
        return contentType;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    



    // === Equals & HashCode ===

    public boolean equals(Object obj) {
        if (!(obj instanceof Attachment)) {
            return false;
        }
        if (obj == this) { return true; }
        return new EqualsBuilder()
                    .append(name, ((Attachment)obj).getName())
                    .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(name)
                    .toHashCode();
    }
}
