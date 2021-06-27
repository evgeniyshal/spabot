package com.worldofmassage.spabot;

import com.worldofmassage.spabot.config.bot.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.

@SpringBootApplication
public class SpabotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpabotApplication.class, args);

		TelegramBotsApi telegramBot = new TelegramBotsApi();
		BotConfig bot = new BotConfig ();
		try {
			telegramBot.registerBot(bot);
		} catch (TelegramApiException e) {
			System.out.println("Бот не создан");
		}

	}

}
