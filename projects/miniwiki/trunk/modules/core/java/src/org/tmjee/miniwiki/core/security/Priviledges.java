package org.tmjee.miniwiki.core.security;

/**
 * @author tmjee
 * @version $Date$ $Id$
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
