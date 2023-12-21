package ua.vholovetskyi.telegrambot.booking.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInputCommand {
    private Long id;
    private String userName;
    private String command;

}
