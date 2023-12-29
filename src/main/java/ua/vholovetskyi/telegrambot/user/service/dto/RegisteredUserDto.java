package ua.vholovetskyi.telegrambot.user.service.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ua.vholovetskyi.telegrambot.user.model.User;
import ua.vholovetskyi.telegrambot.user.model.UserRole;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserDto {

    private Long chatId;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^\\+?3?8?(0\\d{9})$")
    private String phoneNumber;
    private UserRole role;
    private Timestamp registeredAt;

    public User toUser() {
        return User.builder()
                .chatId(chatId)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .role(role)
                .registeredAt(registeredAt)
                .build();
    }
}
