package ua.vholovetskyi.telegrambot.shared.utils;

import java.time.LocalDateTime;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-23
 */
public class RenameFiles {

    private RenameFiles() {
        throw new UnsupportedOperationException();
    }
    public static String renameFile(String fullName, LocalDateTime dateOfVisit) {
        String dateTimeFileFormatter = DateTimeFormatters.localDateTimeFileFormatter(dateOfVisit);
        String str = fullName.replace(" ", "");
        return str + dateTimeFileFormatter +".txt";
    }

}
