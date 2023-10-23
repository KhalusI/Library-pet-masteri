package library.library.enums;

import lombok.Getter;

public enum Permission {
    ADMIN_ROLE("admin"),
    USER_ROLE("user");

    @Getter
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
