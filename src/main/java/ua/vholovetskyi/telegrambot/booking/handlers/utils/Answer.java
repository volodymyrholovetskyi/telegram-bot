package ua.vholovetskyi.telegrambot.booking.handlers.utils;

import com.vdurmont.emoji.EmojiParser;

public class Answer {

    private Answer() {
        throw new UnsupportedOperationException();
    }
    public static final String BLUSH = EmojiParser.parseToUnicode(":blush:");
    public static final String ANSWER_START_COMMAND = "Hello, %s %s%nWelcome to our bot!" +
            "%nUse the /help command to view a list of commands.";
    public static final String ANSWER_HELP_COMMAND =
            "To book a ticket, execute the following two commands:" +
            "%n/myContact - your phone number" +
            "%n/date_of_visit - dd/mm/yyyy hh:mm";
    public static final String ANSWER_DATE_VALID = "Great! We saved your visit date.";
    public static final String ANSWER_DATE_INVALID = "Date is invalid: %s%n" +
            "Please call the command /help and check the date format";
    public static final String ANSWER_CONTACT_VALID = "Great! We saved your phone number.";
    public static final String ANSWER_CONTACT_INVALID = "Phone number is invalid: %s%n " +
            "Please call the command /help and check the phone number format";
}