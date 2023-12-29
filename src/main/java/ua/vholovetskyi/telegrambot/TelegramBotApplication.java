package ua.vholovetskyi.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ua.vholovetskyi.telegrambot.operator.OperatorProperties;
import ua.vholovetskyi.telegrambot.report.SchedulerProperties;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-23
 */
@EnableScheduling
@EnableConfigurationProperties({OperatorProperties.class, SchedulerProperties.class})
@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);
    }

}
