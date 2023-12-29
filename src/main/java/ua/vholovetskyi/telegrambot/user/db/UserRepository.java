package ua.vholovetskyi.telegrambot.user.db;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vholovetskyi.telegrambot.user.model.User;
import ua.vholovetskyi.telegrambot.user.model.UserRole;

import java.util.List;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-24
 */
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByChatId(Long chatId);

    List<User> findByRole(UserRole role);
}
