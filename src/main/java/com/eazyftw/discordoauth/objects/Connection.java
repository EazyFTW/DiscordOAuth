package com.eazyftw.discordoauth.objects;

import com.eazyftw.discordoauth.objects.enums.VisibilityType;

public class Connection {

    private String id, name, type;
    private boolean revoked, verified, friend_sync, show_activity;
    private int visibility;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public boolean isVerified() {
        return verified;
    }

    public boolean isFriendSync() {
        return friend_sync;
    }

    public boolean isShowActivity() {
        return show_activity;
    }

    public int getVisibility() {
        return visibility;
    }

    public VisibilityType getVisibilityType() {
        return VisibilityType.byId(visibility);
    }
}
