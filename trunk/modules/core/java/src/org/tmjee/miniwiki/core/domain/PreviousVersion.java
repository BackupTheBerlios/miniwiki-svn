package org.tmjee.miniwiki.core.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: May 20, 2008
 * Time: 9:34:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_PREVIOUS_VERSION")
public class PreviousVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "previousVersionIdGenerator")
    @TableGenerator(name="previousVersionIdGenerator", table = "TBL_ID_GENERATOR",
                    pkColumnName = "ID", valueColumnName = "ID_VALUE",
                    pkColumnValue = "TBL_PREVIOUS_VERSION_CURRENT_ID", initialValue = 1,
                    allocationSize = 10)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User modifiedBy;

    @Version
    @Column(name = "VERSION")
    private int version;
}
