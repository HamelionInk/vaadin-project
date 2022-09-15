package com.nikitin.vaadinproject.util.enums;

public enum Role {
    USER("USER"),
    ADMIN("NAME");

    private final String enumName;

    Role(String enumName) {
        this.enumName = enumName;
    }

    public String getEnumName() {
        return enumName;
    }
}
