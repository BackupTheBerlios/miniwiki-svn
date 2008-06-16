package org.tmjee.miniwiki.core.security;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 3:12:49 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Priviledges {

    WIKI_CREATE,
    WIKI_EDIT,
    WIKI_VIEW,
    WIKI_COMMENT,
    WIKI_ADMIN,
    WIKI_DELETE,

    SPACE_CREATE,
    SPACE_EDIT,
    SPACE_VIEW,
    SPACE_COMMENT,
    SPACE_ADMIN,
    SPACE_DELETE,

    PAGE_CREATE,
    PAGE_EDIT,
    PAGE_VIEW,
    PAGE_COMMENT,
    PAGE_ADMIN,
    PAGE_DELETE,

    ANONYMOUS_REGISTER,
    ANONYMOUS_CREATE,
    ANONYMOUS_EDIT,
    ANONYMOUS_VIEW,
    ANONYMOUS_COMMENT,
    ANONYMOUS_ADMIN,
    ANONYMOUS_DELETE
    
}
