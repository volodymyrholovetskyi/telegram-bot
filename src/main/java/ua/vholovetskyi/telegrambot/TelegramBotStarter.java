package ua.vholovetskyi.telegrambot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.vholovetskyi.telegrambot.booking.service.BookingTicketService;
import ua.vholovetskyi.telegrambot.handlers.*;
import ua.vholovetskyi.telegrambot.shared.excetpion.ApplicationException;
import ua.vholovetskyi.telegrambot.user.model.UserRole;
import ua.vholovetskyi.telegrambot.user.service.UserService;
import ua.vholovetskyi.telegrambot.user.service.dto.RegisteredUserDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-23
 */
@Component
@Slf4j
public class TelegramBotStarter extends TelegramLongPollingBot {

    private final BotConfig config;
    private final BookingTicketService bookingTicket;
    private final UserService userService;
    private final List<CommandHandler> handlers;

    public TelegramBotStarter(BookingTicketService bookingTicket, UserService userService, BotConfig config) {
        this.bookingTicket = bookingTicket;
        this.userService = userService;
        this.config = config;
        this.handlers = List.of(
                new StartCommandHandler(),
                new HelpCommandHandler(),
                new RegisterCommandHandler(userService),
                new DateVisitCommandHandler(bookingTicket)
        );
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                if (update.getMessage().hasContact()) {
                    final var registeredUserDto = toRegisteredUserDto(update);
                    userService.registered(registeredUserDto);
                }
                if (update.getMessage().hasText()) {
                    final var userInput = toUserInput(update);
                    final var sendMessage = handleCommand(userInput);
                    sendMessage(sendMessage);
                }
            }
        } catch (ApplicationException e) {
            log.warn("Application exception: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("Validation exception: " + e.getMessage());
        } catch (Exception e) {
            log.error("Another error: " + e.getMessage());
        }
    }

    /**
     * This method handles commands entered by the user. This method handlers the commands entered by the user,
     * if no corresponding handler is found, it returns the message this command is not handler.
     * @param userInput Contains user data
     * @return SendMessage Information from the handler about the successful processing of the command or vice versa
     */
    private SendMessage handleCommand(final UserInput userInput) {
        log.info("Starting command processing from user with ID: " + userInput.getChatId());
        Optional<CommandHandler> currentHandler = Optional.empty();
        for (CommandHandler handler : handlers) {
            if (handler.supports(userInput.getCommand())) {
                currentHandler = Optional.of(handler);
                break;
            }
        }
        return currentHandler
                .orElseThrow(() -> new IllegalArgumentException(
                        "Sorry, command was not recognized: " + userInput.getCommand()))
                .handle(userInput);
    }

    public void sendMessage(final SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new ApplicationException("Error in method sendMessage()");
        }
    }

    private RegisteredUserDto toRegisteredUserDto(Update update) {
        return RegisteredUserDto.builder()
                .chatId(update.getMessage().getChatId())
                .firstName(update.getMessage().getContact().getFirstName())
                .lastName(update.getMessage().getContact().getLastName())
                .phoneNumber(update.getMessage().getContact().getPhoneNumber())
                .role(UserRole.ROLE_USER)
                .registeredAt(new Timestamp((long) update.getMessage().getDate() * 1000))
                .build();
    }

    private UserInput toUserInput(final Update update) {
        return new UserInput(
                update.getMessage().getChatId(),
                update.getMessage().getChat().getFirstName(),
                update.getMessage().getChat().getLastName(),
                new Timestamp(update.getMessage().getDate()),
                update.getMessage().getText()
        );
    }
}
