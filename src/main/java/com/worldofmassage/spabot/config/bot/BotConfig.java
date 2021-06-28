package com.worldofmassage.spabot.config.bot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Configuration
//@ConfigurationProperties(prefix = "bot.config")
//
//public class BotConfig extends TelegramLongPollingBot {
//    String botUserName;
//    String token;
//
//
//    public BotConfig() {
//    }
//
//    public void setBotUserName(String botUserName) {
//        this.botUserName = botUserName;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    @Override
//    public String getBotUsername() {
//        return botUserName;
//    }
//
//    @Override
//    public String getBotToken() {
//        return token;
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            SendMessage message = new SendMessage();
//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText(update.getMessage().getText());
//
//            try {
//                execute(message);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
