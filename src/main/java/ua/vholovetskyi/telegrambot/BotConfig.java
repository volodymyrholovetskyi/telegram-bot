package ua.vholovetskyi.telegrambot;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-23
 */
@Configuration
@Data
public class BotConfig {

    @Value("${telegram.bot.name}")
    private String name;
    @Value("${telegram.bot.token}")
    private String token;

}
