package ua.vholovetskyi.telegrambot.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.vholovetskyi.telegrambot.user.model.User;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class RegisteredUser {

    private Long chatId;
    private String firstName;
    private String lastName;

    private String phoneNumber;
    private Timestamp registeredAt;

    public User toUser() {
        return User.builder()
                .chatId(chatId)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .registeredAt(registeredAt)
                .build();
    }
}
