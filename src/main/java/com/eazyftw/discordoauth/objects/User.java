package com.eazyftw.discordoauth.objects;

import com.eazyftw.discordoauth.objects.enums.PremiumType;

public class User {

    private String id, username, discriminator, avatar, banner, locale, email;
    private int accent_color, flags, premium_type, public_flags;
    private boolean bot, mfa_enabled, system, verified;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isAvatarAnimated() {
        return avatar.startsWith("a_");
    }

    public String getAvatarUrl() {
        return "https://cdn.discordapp.com/avatars/" + id + "/" + avatar + "." + (isAvatarAnimated() ? "gif" : "png");
    }

    public String getBanner() {
        return banner;
    }

    public boolean isBannerAnimated() {
        return banner.startsWith("a_");
    }

    public String getBannerUrl() {
        return "https://cdn.discordapp.com/avatars/" + id + "/" + banner + "." + (isBannerAnimated() ? "gif" : "png");
    }

    public String getLocale() {
        return locale;
    }

    public String getEmail() {
        return email;
    }

    public int getAccentColor() {
        return accent_color;
    }

    public int getFlags() {
        return flags;
    }

    public PremiumType getPremiumType() {
        return PremiumType.byId(premium_type);
    }

    public int getPublicFlags() {
        return public_flags;
    }

    public boolean isBot() {
        return bot;
    }

    public boolean isMfaEnabled() {
        return mfa_enabled;
    }

    public boolean isSystem() {
        return system;
    }

    public boolean isVerified() {
        return verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", discriminator='" + discriminator + '\'' +
                ", avatar='" + avatar + '\'' +
                ", banner='" + banner + '\'' +
                ", locale='" + locale + '\'' +
                ", email='" + email + '\'' +
                ", accent_color=" + accent_color +
                ", flags=" + flags +
                ", premium_type=" + premium_type +
                ", public_flags=" + public_flags +
                ", bot=" + bot +
                ", mfa_enabled=" + mfa_enabled +
                ", system=" + system +
                ", verified=" + verified +
                '}';
    }
}
