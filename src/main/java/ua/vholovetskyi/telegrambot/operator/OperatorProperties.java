package ua.vholovetskyi.telegrambot.operator;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.sql.Timestamp;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-26
 */
@Value
@ConfigurationProperties("telegram.bot.operator")
public class OperatorProperties {

    Long chatId;
    String firstName;
    String lastName;
    String phoneNumber;
    Timestamp registeredAt;

}
