package ua.vholovetskyi.telegrambot.booking.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.vholovetskyi.telegrambot.booking.config.BotConfig;
import ua.vholovetskyi.telegrambot.booking.handlers.*;
import ua.vholovetskyi.telegrambot.user.dto.RegisteredUserDto;
import ua.vholovetskyi.telegrambot.user.service.UserService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TelegramBotService extends TelegramLongPollingBot {

    private final InvoiceService ticketGenerator;
    private final BookingTicketService bookingTicket;
    private final UserService userService;
    private final BotConfig config;
    private final List<CommandHandler> handlers;

    public TelegramBotService(InvoiceService invoiceService, BookingTicketService bookingTicket, UserService userService, BotConfig config) {
        this.ticketGenerator = invoiceService;
        this.bookingTicket = bookingTicket;
        this.userService = userService;
        this.config = config;
        this.handlers = List.of(
                new StartCommandHandler(userService),
                new HelpCommandHandler(),
                new ContactCommandHandler(userService),
                new DateOfVisitCommandHandler(bookingTicket)
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
        if (update.hasMessage()) {
            if (update.getMessage().hasContact()) {
                final var registeredUserDto = toRegisteredUserDto(update);
                userService.registered(registeredUserDto);
            }
            if (update.getMessage().hasText()) {
                final var userInput = toUserInput(update);
                final var responseMessage = handleCommand(userInput);
                sendMessage(responseMessage);
            }
        }
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException();
        }
    }


    private RegisteredUserDto toRegisteredUserDto(Update update) {
        return new RegisteredUserDto(
                update.getMessage().getChatId(),
                update.getMessage().getContact().getFirstName(),
                update.getMessage().getContact().getLastName(),
                update.getMessage().getContact().getPhoneNumber(),
                new Timestamp((long) update.getMessage().getDate() * 1000));
    }

    private SendMessage handleCommand(final UserInput userInput) {
        Optional<CommandHandler> currentHandler = Optional.empty();
        for (CommandHandler handler : handlers) {
            if (handler.supports(userInput.getCommand())) {
                currentHandler = Optional.of(handler);
                break;
            }
        }
        return currentHandler
                .orElseThrow(() -> new IllegalArgumentException("Sorry, command was not recognized: " + userInput.getCommand()))
                .handle(userInput);
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
