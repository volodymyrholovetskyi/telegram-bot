package ua.vholovetskyi.telegrambot.user.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.vholovetskyi.telegrambot.user.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByChatId(Long chatId);

//    @Query("SELECT '*' FROM" +
//            "  User user" +
//            " WHERE" +
//            " user.phoneNumber IS NOT NULL")
    List<User> findUsersByPhoneNumberIsNotNull();
}
