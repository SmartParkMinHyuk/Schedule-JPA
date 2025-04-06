package org.example.schedulespringdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulespringdatajpa.domain.Schedule;
import org.example.schedulespringdatajpa.domain.User;
import org.example.schedulespringdatajpa.dto.schedule.ScheduleRequest;
import org.example.schedulespringdatajpa.dto.schedule.ScheduleResponse;
import org.example.schedulespringdatajpa.repository.ScheduleRepository;
import org.example.schedulespringdatajpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponse create(ScheduleRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Schedule schedule = Schedule.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();

        Schedule saved = scheduleRepository.save(schedule);
        return new ScheduleResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponse> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponse::new)
                .toList();
    }

    @Transactional
    public ScheduleResponse update(Long id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 직접 필드 업데이트로 변경
        schedule.update(request.getTitle(), request.getContent(), user);

        Schedule updated = scheduleRepository.save(schedule);
        return new ScheduleResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
