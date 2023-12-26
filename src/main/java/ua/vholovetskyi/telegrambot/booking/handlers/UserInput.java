package ua.vholovetskyi.telegrambot.booking.handlers;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInput {

    private static final String EMPTY = null;
    private Long chatId;
    private String firstName;
    private String lastName;
    private Timestamp registeredAt;
    private String command;
    private String value;

    public UserInput(Long chatId, String firstName, String lastName, Timestamp registeredAt, String command) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredAt = registeredAt;
        final var split = command.split("-");
        if (split.length == 1) {
            this.command = split[0];
            this.value = EMPTY;
        } else {
            this.command = split[0].trim();
            this.value = split[1].trim();
        }
    }
}
