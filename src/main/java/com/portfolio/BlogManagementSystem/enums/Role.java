package com.portfolio.BlogManagementSystem.enums;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.BLOG_READ, Permissions.BLOG_DELETE)),
    USER(Set.of(Permissions.BLOG_READ, Permissions.BLOG_WRITE, Permissions.BLOG_DELETE));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
