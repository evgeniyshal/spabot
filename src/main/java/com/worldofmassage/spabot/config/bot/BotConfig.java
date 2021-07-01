package com.worldofmassage.spabot.config.bot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "bot.config")
@Configuration
public class BotConfig {

    String botUserName;
    String token;

    public BotConfig() {
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBotUserName() {
        return botUserName;
    }

    public String getToken() {
        return token;
    }
}