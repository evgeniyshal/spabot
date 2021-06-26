package com.worldofmassage.spabot;

import com.worldofmassage.spabot.config.bot.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class SpabotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpabotApplication.class, args);
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

	}

}

