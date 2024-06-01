package com.example.demo.role;

public enum RoleType {
    ADMIN("ADMIN"),
    USER("USER");

    public String value;

    RoleType (String role) {
        this.value = role;
    }

    public RoleType getValuesOf(String role) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.value.equals(role)) {
                return roleType;
            }
        }

        return null;
    }
}
