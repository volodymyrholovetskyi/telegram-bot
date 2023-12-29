package ua.vholovetskyi.telegrambot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-23
 */
public interface CommandHandler {

    /**
     * Checks if the CommandHandler handles the given command
     * @param command Command from the user
     * @return Boolean Return true if the handler can handle the user's command and false if not
     */
    boolean supports(String command);

    /**
     * This method handles commands entered by the user.
     * @param userInput Contains user data
     * @return SendMessage Information from the handler about the successful processing of the command or vice versa
     */
    SendMessage handle(UserInput command);
}
