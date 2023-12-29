package ua.vholovetskyi.telegrambot.shared.excetpion;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-26
 */
public class ResourcesNotFound extends RuntimeException {
    public ResourcesNotFound(Long id) {
        super("Resources with ID: " + id + "not found!");
    }
}
