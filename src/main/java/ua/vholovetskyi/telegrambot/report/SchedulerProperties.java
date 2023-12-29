package ua.vholovetskyi.telegrambot.report;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-26
 */
@Value
@ConfigurationProperties("telegram.bot.scheduler")
public class SchedulerProperties {
    String cron;
}
