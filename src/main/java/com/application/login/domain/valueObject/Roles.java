package com.application.login.domain.valueObject;

public enum Roles {

    BASIC(2L), ADMIN(1L);

    long roleId;

    Roles(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }
}
