package org.example.schedulespringdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulespringdatajpa.domain.User;
import org.example.schedulespringdatajpa.dto.user.UserRequest;
import org.example.schedulespringdatajpa.dto.user.UserResponse;
import org.example.schedulespringdatajpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // 암호화 미적용 상태
                .build();

        return new UserResponse(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .toList();
    }

    @Transactional
    public UserResponse update(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        user = User.builder()
                .id(user.getId())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        return new UserResponse(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
