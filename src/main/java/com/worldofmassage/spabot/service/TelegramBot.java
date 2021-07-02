package com.worldofmassage.spabot.service;

import com.worldofmassage.spabot.config.bot.BotConfig;
import com.worldofmassage.spabot.entity.Offer;
import com.worldofmassage.spabot.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;
    private final OfferRepository offerRepository;
    private List<Offer> offers;

    @Autowired
    public TelegramBot(BotConfig config, OfferRepository offerRepository) {
        botUsername = config.getBotUserName();
        botToken = config.getToken();
        this.offerRepository = offerRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        if ("/offers".equals(command)) {
            try {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(update.getMessage().getChatId()));
                message.setReplyMarkup(getKeyboard(command));
                message.setText("Выберите услугу чтобы получить подробную информацию по ней");
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (offerRepository.findByOrderByIdAsc().stream().anyMatch(offer -> offer.getTitle().equals(command))) {
            try {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(update.getMessage().getChatId()));
                message.setText(getMessageText(command));
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            try {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(update.getMessage().getChatId()));
                message.setText("Введите /offers чтобы получить список услуг нашего спа центра");
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private ReplyKeyboardMarkup getKeyboard(String command) {
        if ("/offers".equals(command)) {
            List<KeyboardRow> keyboardRows = new ArrayList<>();
            for (Offer offer : offerRepository.findByOrderByIdAsc()) {
                KeyboardRow row = new KeyboardRow();
                row.add(offer.getTitle());
                keyboardRows.add(row);
            }
            return new ReplyKeyboardMarkup(keyboardRows);
        }
        return new ReplyKeyboardMarkup();
    }

    private String getMessageText(String command) {
        StringBuilder builder = new StringBuilder();
        Offer offer = offerRepository.findByTitle(command);
        builder
                .append(offer.getTitle())
                .append("\n")
                .append(offer.getDescription())
                .append("\n")
                .append(offer.getPrice());
        return builder.toString();
    }

    @Scheduled(fixedRate = 1800000)
    private void updateOffers() {
        offers = offerRepository.findByOrderByIdAsc();
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}