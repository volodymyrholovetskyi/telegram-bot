package ua.vholovetskyi.telegrambot.booking.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class UserInput {
    private Long chatId;
    private String firstName;
    private String lastName;
    private Timestamp registeredAt;
    private String value;
    private String command;

}
