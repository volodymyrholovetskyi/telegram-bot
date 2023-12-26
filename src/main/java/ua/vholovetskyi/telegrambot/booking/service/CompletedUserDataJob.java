package ua.vholovetskyi.telegrambot.booking.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.vholovetskyi.telegrambot.user.db.UserRepository;
import ua.vholovetskyi.telegrambot.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class CompletedUserDataJob {

    private final InvoiceService generatorService;
    private final UserRepository userRepository;


    @Transactional
    @Scheduled(cron = "0 */1 * ? * *")
    public void run() {
        System.out.println("Last run: " + LocalDateTime.now());
        List<User> usersByPhoneNumberIsNotNull = userRepository.findUsersByPhoneNumberIsNotNull();
        usersByPhoneNumberIsNotNull.forEach(System.out::println);
    }
}
