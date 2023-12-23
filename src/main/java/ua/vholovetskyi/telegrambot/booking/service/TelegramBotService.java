package ua.vholovetskyi.telegrambot.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.vholovetskyi.telegrambot.booking.config.BotConfig;
import ua.vholovetskyi.telegrambot.booking.handlers.*;
import ua.vholovetskyi.telegrambot.user.service.UserService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {

    private final BookingTicketService bookingTicket;
    private final TicketGeneratorService ticketGenerator;
    private final MessageParser messageParser;
    private final UserService userService;
    private final BotConfig config;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().getText().equals("/myContact")) {
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

        if (update.hasMessage() && update.getMessage().hasText()) {
            final var userInput = toUserInput(update);

            if (update.getMessage().isCommand()) {
                final var responseMessage = handleCommand(userInput);
                sendMessage(responseMessage);
            } else {
                final var bookingTicket = messageParser.parseMessage(update.getMessage().getText());
                this.bookingTicket.saveBookingTicket(bookingTicket);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    private ResponseMessage handleCommand(final UserInput userInput) {
        final List<CommandHandler> handlers = new ArrayList<>();
        handlers.add(new StartCommandHandler(userService));
        handlers.add(new HelpCommandHandler());
        handlers.add(new ContactCommandHandler());
        handlers.add(new DateCommandHandler());
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

    private void sendMessage(ResponseMessage responseMessage) {
        final var message = new SendMessage();
        message.setChatId(responseMessage.getId());
        message.setText(responseMessage.getText());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException();
        }
    }

    private UserInput toUserInput(final Update update) {
        String[] strings = parseCommand(update.getMessage().getText());
        return new UserInput(
                update.getMessage().getChatId(),
                update.getMessage().getChat().getFirstName(),
                update.getMessage().getChat().getLastName(),
                new Timestamp(update.getMessage().getDate()),
                strings[1].trim(),
                strings[0].trim()
        );
    }

    private String[] parseCommand(String command) {
        String[] split = command.split("-");

        return split;
    }


    private void startCommand(long chatId, String name) {

        String answer = String.format("Hello %s!%nWelcome to the Ticket Booking Service.%nEnter required data or use the /help command for more information", name);

//        sendMessage(chatId, answer);
    }

    private void helpCommand(long chatId) {
        String answer = String.format("To book a ticket, enter the required data in the following format: %n1. Phone number - XXXYYYYYYY; %n2. Data of visit - dd/mm/yyyy hh:mm; %n3. First name and last name.");
//        sendMessage(chatId, answer);
    }


}
