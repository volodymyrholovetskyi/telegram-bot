package ua.vholovetskyi.telegrambot.operator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.vholovetskyi.telegrambot.TelegramBotStarter;
import ua.vholovetskyi.telegrambot.booking.model.Ticket;
import ua.vholovetskyi.telegrambot.user.model.User;
import ua.vholovetskyi.telegrambot.user.model.UserRole;
import ua.vholovetskyi.telegrambot.user.service.UserService;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationOperator {

    private final TelegramBotStarter telegramBotStarter;
    private final UserService userService;

    public void notifyOperator(Ticket ticket) {
        final var operators = userService.findByRole(UserRole.ROLE_OPERATOR);
        final var user = userService.findById(ticket.getUserId());
        if (!operators.isEmpty()) {
            for (User operator : operators) {
                final var message = new SendMessage();
                message.setChatId(operator.getChatId());
                message.setText(textFormat(user));
                telegramBotStarter.sendMessage(message);
                log.info("A notification was sent to the operator with ID: " + operator.getChatId());
            }
        }
    }

    private String textFormat(User operator) {
        final var text = "User: %s booked a ticket!";
        return String.format(text, operator.getFullName());
    }
}
