package ua.vholovetskyi.telegrambot.booking.service;

import org.springframework.stereotype.Component;
import ua.vholovetskyi.telegrambot.booking.service.dto.CreateBookingTicket;
import ua.vholovetskyi.telegrambot.booking.validator.Validator;

@Component
public class MessageParser {

    public CreateBookingTicket parseMessage(final String message) {
        final var split = message.split("\\s");
        if (split.length != 4) {
            throw new IllegalArgumentException("Not enough parameters");
        }

//        for (String s : split) {
//            char[] chars = s.toCharArray();
//            for (int i = 0; i < chars.length; i++) {
//                if (Character.isDigit(chars[i])) {
//                    System.out.println("Is digit");
//                }
//            }
//        }
        return null;
    }

}
