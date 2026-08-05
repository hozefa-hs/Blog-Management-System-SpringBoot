package com.portfolio.BlogManagementSystem.enums;

import lombok.Getter;

import java.util.Set;

@Getter
public enum Role {
    ADMIN(Set.of(Permissions.BLOG_READ, Permissions.BLOG_DELETE)),
    USER(Set.of(Permissions.BLOG_READ, Permissions.BLOG_WRITE, Permissions.BLOG_DELETE));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

}
