package com.worldofmassage.spabot.service;

import com.worldofmassage.spabot.config.bot.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;

    @Autowired
    public TelegramBot(BotConfig config) {
        botUsername = config.getBotUserName();
        botToken = config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("Hello World!");
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}