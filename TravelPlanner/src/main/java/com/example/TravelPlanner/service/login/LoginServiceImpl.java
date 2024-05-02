package com.example.TravelPlanner.service.login;

import com.example.TravelPlanner.domain.User;
import com.example.TravelPlanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
//파일은 하나의 역할만 담당
//서비스 인터페이스는 비즈니스 로직에 대한 추상화 제공
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;

    @Override
    public boolean validateUser(String userId, String password) {
        Optional<User> optionalUser = userRepository.findByUserid(userId);
        System.out.println("User ID: " + userId);
        System.out.println("Password: " + password);

        if (!optionalUser.isPresent()) {
            System.out.println("User not found");
            return false;
        }

        User user = optionalUser.get();

        if (password.equals(user.getPassword())) {
            System.out.println("Login successful");
            return true;
        } else {
            System.out.println("Incorrect password");
            return false;
        }
    }


}
