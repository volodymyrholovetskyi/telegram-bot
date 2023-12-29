package ua.vholovetskyi.telegrambot.handlers;

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
        final var array = command.split("-");
        if (array.length == 1) {
            this.command = array[0];
            this.value = EMPTY;
        }
        if (array.length == 2) {
            this.command = array[0].trim();
            this.value = array[1].trim();
        }
    }
}
