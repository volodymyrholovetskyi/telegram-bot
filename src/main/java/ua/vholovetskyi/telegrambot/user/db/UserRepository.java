package ua.vholovetskyi.telegrambot.user.db;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vholovetskyi.telegrambot.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByChatId(Long chatId);
}
