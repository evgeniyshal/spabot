package com.worldofmassage.spabot.config.bot;

import com.worldofmassage.spabot.service.TelegramBot;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
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

    @Bean
    @Qualifier("bot")
    public TelegramBot threadPoolTaskExecutor(){
        TelegramBot bot = new TelegramBot();
        bot.setBotToken(token);
        bot.setBotUsername(botUserName);
        return bot;
    }
}
