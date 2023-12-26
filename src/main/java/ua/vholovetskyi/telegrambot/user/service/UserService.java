package ua.vholovetskyi.telegrambot.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.telegrambot.booking.dto.AddDateOfVisit;
import ua.vholovetskyi.telegrambot.user.db.UserRepository;
import ua.vholovetskyi.telegrambot.user.dto.RegisteredUser;
import ua.vholovetskyi.telegrambot.user.dto.RegisteredUserDto;
import ua.vholovetskyi.telegrambot.user.dto.UpdatePhoneUser;
import ua.vholovetskyi.telegrambot.user.model.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long chatId) {
        return userRepository.findById(chatId).orElseThrow();
    }
    public void registered(RegisteredUserDto userDto) {
        userRepository.save(userDto.toUser());
    }

    public boolean existsByChatId(Long chatId){
        return userRepository.existsByChatId(chatId);
    }


    @Transactional
    public void updatePhoneNumber(UpdatePhoneUser phoneUser) {
        User userUpdate = findById(phoneUser.getChatId());
        userUpdate.updatePhoneNumber(phoneUser.getPhoneNumber());
    }
}
