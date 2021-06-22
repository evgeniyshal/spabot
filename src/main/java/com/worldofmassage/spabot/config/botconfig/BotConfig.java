package com.worldofmassage.spabot.config.botconfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("classpath:application.properties")
public class BotConfig {


    @Value("${WorldOfMassage_bot}")
    String botUserName;


    @Value("${1872997526:AAEIe18H78KX3S7Qse7TVqxxXARkX4E8JGc}")
    String token;
}
