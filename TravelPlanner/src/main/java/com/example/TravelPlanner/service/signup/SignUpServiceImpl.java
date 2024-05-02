package com.example.TravelPlanner.service.signup;

import com.example.TravelPlanner.domain.User;
import com.example.TravelPlanner.dto.signup.SignUpDto;
import com.example.TravelPlanner.dto.signup.ValidateUseridDto;
import com.example.TravelPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SignUpDto join(SignUpDto signUpDto) {
        // DTO를 Entity로 변환하여 저장
        User user = new User();
        user.setId(signUpDto.getId());
        user.setName(signUpDto.getName());
        user.setUserid(signUpDto.getUserid());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        userRepository.save(user);

        // 로그 확인
        System.out.println("회원가입 완료: " + user);

        return signUpDto;
    }

    @Override
    public ValidateUseridDto checkUserid(ValidateUseridDto validateUseridDto) {
        boolean exists = userRepository.existsByUserid(validateUseridDto.getUserid());
        validateUseridDto.setExists(exists);
        return validateUseridDto;
    }
}
