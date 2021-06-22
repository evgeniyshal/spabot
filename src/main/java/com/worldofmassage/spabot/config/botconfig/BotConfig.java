package com.worldofmassage.spabot.config.botconfig;

import org.springframework.beans.factory.annotation.Value;

public class BotConfig {


    @Value("${WorldOfMassage_bot}")
    String botUserName;


    @Value("${1872997526:AAEIe18H78KX3S7Qse7TVqxxXARkX4E8JGc}")
    String token;
}
