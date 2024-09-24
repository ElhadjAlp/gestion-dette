package com.ism.data.enums;

public enum RoleEnum {
    BOUTIQUIER, CLIENT, ADMIN;

    public static RoleEnum getValue(String roleName) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.name().compareToIgnoreCase(roleName) == 0) {
                return role;
            }
        }
        return null;

    }
}
