package org.example.schedulespringdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulespringdatajpa.domain.User;
import org.example.schedulespringdatajpa.dto.user.UserSignupRequest;
import org.example.schedulespringdatajpa.dto.user.UserResponse;
import org.example.schedulespringdatajpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse signup(UserSignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // 현재는 평문 저장
                .build();

        return new UserResponse(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserResponse login(String email, String password) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 틀렸습니다."));

        return new UserResponse(user);
    }
}
