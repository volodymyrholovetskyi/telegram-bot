package ua.vholovetskyi.telegrambot.booking.handlers;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ua.vholovetskyi.telegrambot.booking.handlers.ResponseMessage;
import ua.vholovetskyi.telegrambot.booking.handlers.UserInput;

import java.util.ArrayList;
import java.util.List;

public class ContactButton {

    public ResponseMessage sendContactButton(UserInput inputCommand) {
        KeyboardButton contactButton = new KeyboardButton("Send contact");
        contactButton.setRequestContact(true);

        KeyboardRow row = new KeyboardRow();
        row.add(contactButton);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(keyboard);
        markup.setResizeKeyboard(true);

        return new ResponseMessage(inputCommand.getChatId(), false, "Click the button below to save your contact information");
        }
}
