package ua.vholovetskyi.telegrambot.handlers;

public abstract class BaseCommandHandler implements CommandHandler{

    @Override
    public boolean supports(String name) {
        return getCommandName().equals(name);
    }

    protected abstract String getCommandName();
}
