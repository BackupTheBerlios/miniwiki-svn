package org.tmjee.miniwiki.core.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 20, 2008
 * Time: 9:34:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_ATTACHEMENT")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "attachmentIdGenerator")
    @TableGenerator(name="attachmentIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_ATTACHMENT_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Basic(optional = false)
    @Column(name = "CONTENT_TYPE", nullable = false)
    private String contentType;

    @Lob
    @Column(name = "ATTACHMENT", nullable = false)
    private byte[] attachment;

    @Version
    @Column(name = "VERSION")
    private int version;

}
