[![Jitpack build](https://jitpack.io/v/EazyFTW/DiscordOAuth.svg)](https://jitpack.io/#EazyFTW/DiscordOAuth)
[![GitHub licence](https://img.shields.io/github/license/EazyFTW/DiscordOAuth)](https://github.com/EazyFTW/DiscordOAuth/blob/master/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/EazyFTW/DiscordOAuth)](https://github.com/EazyFTW/DiscordOAuth/issues)
[![GitHub forks](https://img.shields.io/github/forks/EazyFTW/DiscordOAuth)](https://github.com/EazyFTW/DiscordOAuth/network)
[![GitHub stars](https://img.shields.io/github/stars/EazyFTW/DiscordOAuth)](https://github.com/EazyFTW/DiscordOAuth/stargazers)

# DiscordOAuth

A little Discord OAuth wrapper for [Discord](https://discordapp.com).

## Features
* Get a user's user, guild, and connection data.
* Generation of the authorization url.
* Revoking the access-token.
* Refreshing the access-token with refresh-token.

## Examples
Initializing the OAuth builder.
```java
import com.eazyftw.discordoauth.OAuthBuilder;

OAuthBuilder builder = new OAuthBuilder("clientID", "clientSecret")
        .setScopes(new String[]{"connections", "guilds", "email", "identity"})
        .setRedirectURI("redirectURL");
```
Getting a user response.
```java
import com.eazyftw.discordoauth.objects.User;
import com.eazyftw.discordoauth.web.PostResponse;

PostResponse response = builder.exchange("code");

if(response == PostResponse.OK) {
  User user = builder.getUser();
  System.out.println(user.getId());
} else {
  System.out.println("Response error.");
}
```
Refresh the access-token.
```java
import com.eazyftw.discordoauth.objects.User;
import com.eazyftw.discordoauth.web.PostResponse;

PostResponse response = builder.refresh();

if (response == PostResponse.ERROR) {
  System.out.println("Response error.");
} else {
  // EXECUTE CODE
}
```
Revoke the access-token.
```java
import com.eazyftw.discordoauth.objects.User;
import com.eazyftw.discordoauth.web.PostResponse;

PostResponse response = builder.revoke();

if (response == PostResponse.ERROR) {
  System.out.println("Response error.");
} else {
  // EXECUTE CODE
}
```

## Download

Be sure to replace the **VERSION** key below with the jitpack build number shown above, for example `build-2`.

### Maven
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.EazyFTW</groupId>
  <artifactId>DiscordOAuth</artifactId>
  <version>VERSION</version>
</dependency>
```

### Gradle
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
    implementation 'com.github.EazyFTW:DiscordOAuth:VERSION'
}
```

### Release
Just download the latest release [here](https://github.com/EazyFTW/DiscordOAuth/releases). 
