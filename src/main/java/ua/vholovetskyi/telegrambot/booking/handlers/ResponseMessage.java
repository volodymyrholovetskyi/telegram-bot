package ua.vholovetskyi.telegrambot.booking.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {

    private Long id;
    private boolean hasError;
    private String text;
}
