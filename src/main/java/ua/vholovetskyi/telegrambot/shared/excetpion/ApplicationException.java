package ua.vholovetskyi.telegrambot.shared.excetpion;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-23
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }
}
