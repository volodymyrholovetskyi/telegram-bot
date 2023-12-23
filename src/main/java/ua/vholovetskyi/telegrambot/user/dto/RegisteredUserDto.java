package ua.vholovetskyi.telegrambot.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.vholovetskyi.telegrambot.user.model.User;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class RegisteredUserDto {

    private Long chatId;
    private String firstName;
    private String lastName;

    private Timestamp registeredAt;

    public User toUser() {
        return User.builder()
                .chatId(chatId)
                .firstName(firstName)
                .lastName(lastName)
                .registeredAt(registeredAt)
                .build();
    }
}
