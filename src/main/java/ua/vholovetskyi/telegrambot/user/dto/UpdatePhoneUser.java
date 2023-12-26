package ua.vholovetskyi.telegrambot.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePhoneUser {
    private Long chatId;
    private String phoneNumber;
}
