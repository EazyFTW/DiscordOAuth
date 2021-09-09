package com.eazyftw.discordoauth.objects;

import java.util.Arrays;

public class Guild {

    private String id, name, icon;
    private String[] features;
    private int permissions;
    private boolean owner;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isIconAnimated() {
        return icon.startsWith("a_");
    }

    public String getIconUrl() {
        return "https://cdn.discordapp.com/icons/" + id + "/" + icon + "." + (isIconAnimated() ? "gif" : "png");
    }

    public String[] getFeatures() {
        return features;
    }

    public int getPermissions() {
        return permissions;
    }

    public boolean isOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Guild{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", features=" + Arrays.toString(features) +
                ", permissions=" + permissions +
                ", owner=" + owner +
                '}';
    }
}
