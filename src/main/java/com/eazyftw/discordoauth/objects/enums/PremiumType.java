package com.eazyftw.discordoauth.objects.enums;

import java.util.Arrays;

public enum PremiumType {

    NONE(0),
    NITRO_CLASSIC(1),
    NITRO(2);

    private final int id;

    PremiumType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PremiumType byId(int id) {
        return Arrays.stream(PremiumType.values()).filter(n -> n.getId() == id).findFirst().orElse(NONE);
    }
}
