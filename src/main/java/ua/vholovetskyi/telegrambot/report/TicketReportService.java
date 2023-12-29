package ua.vholovetskyi.telegrambot.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.vholovetskyi.telegrambot.TelegramBotStarter;
import ua.vholovetskyi.telegrambot.booking.model.Ticket;
import ua.vholovetskyi.telegrambot.operator.NotificationOperator;
import ua.vholovetskyi.telegrambot.report.exception.TicketGeneratorException;
import ua.vholovetskyi.telegrambot.handlers.HandlerMessage;
import ua.vholovetskyi.telegrambot.shared.utils.DateTimeFormatters;
import ua.vholovetskyi.telegrambot.shared.utils.RenameFiles;
import ua.vholovetskyi.telegrambot.user.model.User;
import ua.vholovetskyi.telegrambot.user.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-26
 */
@Component
@RequiredArgsConstructor
public class TicketReportService {

    private static final String DIRECTORY = "./tickets/";
    private final UserService userService;
    private final TelegramBotStarter telegramBotStarter;
    private final NotificationOperator operatorService;

    /**
     * This method is used to create a ticket and save it to folder.
     * @param ticket The object from which the ticket is generated
     */
    public void generateTicket(final Ticket ticket) {
        final var user = userService.findById(ticket.getUserId());
        final var fileName = RenameFiles.renameFile(user.getFullName(), ticket.getDateOfVisit());
        final var path = Paths.get(DIRECTORY + fileName);
        final var text = textFormat(user, ticket);
        try {
            Files.writeString(path, text, UTF_8);
            sendDocument(path.toString(), ticket);
            operatorService.notifyOperator(ticket);
        } catch (IOException | TelegramApiException e) {
            throw new TicketGeneratorException("An error occurred while generating the ticket: " + e.getMessage());
        }
    }

    private void sendDocument(String fileName, Ticket ticket) throws TelegramApiException {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(ticket.getUserId());
        sendDocument.setDocument(new InputFile(new File(fileName)));
        telegramBotStarter.execute(sendDocument);
    }

    private String textFormat(User user, Ticket ticket) {
        final var sb = new StringBuilder();
        sb.append("Welcome to Circus \"Orbit\"")
                .append(HandlerMessage.BLUSH)
                .append("%n")
                .append("Name: %s%n")
                .append("Status: %s%n")
                .append("Phone number: %s%n")
                .append("Date fo visit: %s%n")
                .append("Thank you for booking a ticket!");
        return String.format(sb.toString(),
                user.getFullName(),
                ticket.getStatus(),
                user.getPhoneNumber(),
                DateTimeFormatters.localDateTimeParser(ticket.getDateOfVisit())
        );
    }
}
