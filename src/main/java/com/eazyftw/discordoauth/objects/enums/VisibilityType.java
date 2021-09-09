package com.eazyftw.discordoauth.objects.enums;

import java.util.Arrays;

public enum VisibilityType {

    NONE(0, "Invisible to everyone except the user themselves."),
    EVERYONE(1, "Visible to everyone.");

    private final int id;
    private final String description;

    VisibilityType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static VisibilityType byId(int id) {
        return Arrays.stream(VisibilityType.values()).filter(n -> n.getId() == id).findFirst().orElse(NONE);
    }
}
