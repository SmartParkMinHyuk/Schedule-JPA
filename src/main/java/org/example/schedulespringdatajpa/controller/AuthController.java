package org.example.schedulespringdatajpa.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedulespringdatajpa.dto.user.UserLoginRequest;
import org.example.schedulespringdatajpa.dto.user.UserSignupRequest;
import org.example.schedulespringdatajpa.dto.user.UserResponse;
import org.example.schedulespringdatajpa.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ✅ 회원가입 엔드포인트
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody UserSignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequest request,
                                        HttpServletRequest httpRequest) {
        try {
            UserResponse user = authService.login(request.getEmail(), request.getPassword());

            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("user", user); // 세션에 유저 정보 저장

            return ResponseEntity.ok("로그인 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body("로그인 실패: " + e.getMessage());
        }
    }

}
