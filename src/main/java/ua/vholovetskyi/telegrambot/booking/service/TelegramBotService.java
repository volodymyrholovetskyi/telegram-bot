package ua.vholovetskyi.telegrambot.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.vholovetskyi.telegrambot.booking.config.BotConfig;
import ua.vholovetskyi.telegrambot.booking.db.BookingTicketRepository;
import ua.vholovetskyi.telegrambot.booking.handlers.*;
import ua.vholovetskyi.telegrambot.user.service.UserService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {

    private final TicketGeneratorService ticketGenerator;
    private final BookingTicketService bookingTicket;
    private final UserService userService;
    private final BotConfig config;

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            final var userInput = toUserInput(update);
            final var responseMessage = handleCommand(userInput);
            final var sendMessage = new SendMessage();
            sendMessage.setChatId(responseMessage.getId());
            sendMessage.setText(responseMessage.getText());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException();
            }
        }
    }
    private ResponseMessage handleCommand(final UserInput userInput) {
        final var handlers = new ArrayList<CommandHandler>();
        handlers.add(new StartCommandHandler(userService));
        handlers.add(new HelpCommandHandler());
        handlers.add(new ContactCommandHandler(bookingTicket));
        handlers.add(new DateCommandHandler(userService));
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


    //            KeyboardButton contactButton = new KeyboardButton("Send contact");
//            contactButton.setRequestContact(true);
//
//            KeyboardRow row = new KeyboardRow();
//            row.add(contactButton);
//
//            List<KeyboardRow> keyboard = new ArrayList<>();
//            keyboard.add(row);
//
//            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
//            markup.setKeyboard(keyboard);
//            markup.setResizeKeyboard(true);

//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(update.getMessage().getChatId());
//            sendMessage.setText("Click 'Send contact' button!");
//            sendMessage.setReplyMarkup(markup);

//            try {
//                execute(sendMessage);
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }

}
