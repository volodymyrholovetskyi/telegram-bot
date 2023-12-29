package ua.vholovetskyi.telegrambot.operator;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.telegrambot.user.service.dto.RegisteredUserDto;
import ua.vholovetskyi.telegrambot.user.model.UserRole;
import ua.vholovetskyi.telegrambot.user.service.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-26
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OperatorInitializer {

    private final OperatorProperties properties;
    private final UserService userService;


    @PostConstruct
    public void createOperator() {
        boolean operatorExists = userService.existsByChatId(properties.getChatId());
        if (!operatorExists) {
            userService.registered(
                    RegisteredUserDto.builder()
                            .chatId(properties.getChatId())
                            .firstName(properties.getFirstName())
                            .lastName(properties.getLastName())
                            .phoneNumber(properties.getPhoneNumber())
                            .role(UserRole.ROLE_OPERATOR)
                            .registeredAt(Timestamp.valueOf(LocalDateTime.now()))
                            .build());
            log.info("Registered operator with ID: " + properties.getChatId());
        }
    }
}
