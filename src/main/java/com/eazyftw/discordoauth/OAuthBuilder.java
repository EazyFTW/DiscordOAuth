package com.eazyftw.discordoauth;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.eazyftw.discordoauth.objects.Connection;
import com.eazyftw.discordoauth.objects.Guild;
import com.eazyftw.discordoauth.objects.User;
import com.eazyftw.discordoauth.web.PostRequest;
import com.eazyftw.discordoauth.web.PostResponse;
import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import okhttp3.OkHttpClient;

public class OAuthBuilder {

    private static final String BASE_URI = "https://discord.com/api/";
    private static final String TOKEN_URI = "oauth2/token";
    private static final String REVOCATION_URI = "oauth2/token/revoke";

    private static final String CONNECTIONS_URI = "users/@me/connections";
    private static final String ME_URI = "users/@me";
    private static final String GUILD_URI = "users/@me/guilds";

    private final OkHttpClient client;

    private final String id, secret;
    private String redirect, scopes, accessToken, refreshToken;

    public OAuthBuilder(String clientId, String clientSecret) {
        this.id = clientId;
        this.secret = clientSecret;

        this.client = new OkHttpClient();
    }

    public OAuthBuilder setRedirectURI(String url) {
        this.redirect = url;
        return this;
    }

    public OAuthBuilder setScopes(String[] scopes) {
        StringBuilder scopesBuilder = new StringBuilder();

        for (String scope : scopes)
            scopesBuilder.append(scope).append("%20");

        this.scopes = scopesBuilder.substring(0, this.scopes.length() - 3);

        return this;
    }

    public String getAuthorizationUrl(String state) {
        StringBuilder builder = new StringBuilder();

        builder.append(BASE_URI);
        builder.append("oauth2/authorize");
        builder.append("?response_type=code");
        builder.append("&client_id=").append(this.id);
        builder.append("&scope=").append(this.scopes);
        if (state != null && state.length() > 0)
            builder.append("&state=").append(state);
        builder.append("&redirect_uri=").append(this.redirect);

        return builder.toString();
    }

    public PostResponse exchange(String code) {
        try {
            String json = PostRequest.exchangePost(client, BASE_URI + TOKEN_URI, this.id, this.secret, code, this.redirect);

            return getPostResponse(json);
        } catch (IOException e) {
            return PostResponse.ERROR;
        }
    }

    public PostResponse refresh() {
        try {
            String json = PostRequest.refreshPost(client, BASE_URI + TOKEN_URI, this.id, this.secret, this.refreshToken, this.redirect);

            return getPostResponse(json);
        } catch (IOException e) {
            return PostResponse.ERROR;
        }
    }

    private PostResponse getPostResponse(String json) {
        try {
            JsonObject object = JsonParser.parseString(json).getAsJsonObject();

            this.accessToken = object.get("access_token").getAsString();
            this.refreshToken = object.get("refresh_token").getAsString();

            return PostResponse.OK;
        } catch (JsonSyntaxException e) {
            return PostResponse.ERROR;
        }
    }

    public PostResponse revoke() {
        try {
            PostRequest.revokePost(client, BASE_URI + REVOCATION_URI, accessToken);

            return PostResponse.OK;
        } catch (IOException e) {
            return PostResponse.ERROR;
        }
    }

    public User getUser() {
        try {
            String json = PostRequest.get(client, BASE_URI + ME_URI, accessToken);

            return new Gson().fromJson(json, User.class);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Guild> getGuilds() {
        try {
            String json = PostRequest.get(client, BASE_URI + GUILD_URI, accessToken);

            return Arrays.asList(new Gson().fromJson(json, Guild[].class));
        } catch (IOException e) {
            return null;
        }
    }

    public List<Connection> getConnections() {
        try {
            String json = PostRequest.get(client, BASE_URI + CONNECTIONS_URI, accessToken);

            return Arrays.asList(new Gson().fromJson(json, Connection[].class));
        } catch (IOException e) {
            return null;
        }
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String access_token) {
        this.accessToken = access_token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refresh_token) {
        this.refreshToken = refresh_token;
    }
}
