package ir.maktab.home_services.service;

import ir.maktab.home_services.exception.InvalidInformationException;
import ir.maktab.home_services.exception.InvalidPasswordException;
import ir.maktab.home_services.exception.NotFoundException;
import ir.maktab.home_services.model.Users;
import ir.maktab.home_services.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Users userSignUp(Users users) {
        if (userRepository.findUserByEmail(users.getEmail()).isPresent())
            throw new NotFoundException(users.getEmail() + "تکراری است ");
        else
            userRepository.save(users);
        return users;
    }

    public Users signIn(String email, String passWord) {
        Optional<Users> userByEmail = userRepository.findUserByEmail(email);
        Optional<Users> userByPassword = userRepository.findByPassword(passWord);

        if (userByEmail.isPresent() && userByPassword.isPresent()) {
            if (userByEmail.get().getPassword().equals(passWord)) {

            } else {
                throw new InvalidInformationException("رمز عبور اشتباه است");
            }
        } else {
            throw new NotFoundException("کاربر پیدا نشد");
        }
        return userByPassword.get();
    }

    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        Users user = userRepository.findById(userId).orElseThrow();
        if (!user.getPassword().equals(oldPassword)) {
            throw new InvalidPasswordException("پسورد قبلی اشتباه است");
        }
        if (!isValidPassword(newPassword)) {
            throw new InvalidPasswordException("پسورد جدید معتبر نیست");
        }
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$");
    }
}

