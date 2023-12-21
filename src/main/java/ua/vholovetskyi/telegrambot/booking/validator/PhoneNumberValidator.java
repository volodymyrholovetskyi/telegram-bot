package ua.vholovetskyi.telegrambot.booking.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements Validator{
    @Override
    public boolean validate(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
