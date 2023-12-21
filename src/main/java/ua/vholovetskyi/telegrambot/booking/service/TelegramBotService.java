package ua.vholovetskyi.telegrambot.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.vholovetskyi.telegrambot.booking.config.BotConfig;
import ua.vholovetskyi.telegrambot.booking.handlers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {

    private final BookingTicketService bookingTicket;
    private final TicketGeneratorService ticketGenerator;
    private final MessageParser messageParser;
    private final BotConfig config;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            final var userInput = toUserInput(update);
            System.out.println(update);

            if (update.getMessage().isCommand()) {
                final var responseMessage = processCommand(userInput);
                sendMessage(responseMessage);
            } else {
                System.out.println(update.getMessage().getContact().getPhoneNumber());
                System.out.println(update.getMessage().getContact().getFirstName());
                System.out.println(update.getMessage().getContact().getLastName());
                System.out.println(update.getMessage().getDate());
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

    private ResponseMessage processCommand(final UserInputCommand userInput) {
        final List<CommandHandler> handlers = new ArrayList<>();
        handlers.add(new StartCommandHandler());
        handlers.add(new HelpCommandHandler());
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

    private UserInputCommand toUserInput(final Update update) {
        return new UserInputCommand(
                update.getMessage().getChatId(),
                update.getMessage().getChat().getFirstName(),
                update.getMessage().getText()
        );
    }


    private void startCommand(long chatId, String name) {
//        String answer = """
//                Hello!
//                Welcome to the ticket booking service.
//                To book a ticket, enter the required data:
//                first name, surname, phone number and date of visit
//                or use the '/help' command for more information.
//                """;

        String answer = String.format("Hello %s!%nWelcome to the Ticket Booking Service.%nEnter required data or use the /help command for more information", name);

//        sendMessage(chatId, answer);
    }

    private void helpCommand(long chatId) {
        String answer = String.format("To book a ticket, enter the required data in the following format: %n1. Phone number - XXXYYYYYYY; %n2. Data of visit - dd/mm/yyyy hh:mm; %n3. First name and last name.");
//        sendMessage(chatId, answer);
    }


}
