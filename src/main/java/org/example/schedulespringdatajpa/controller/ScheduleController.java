package org.example.schedulespringdatajpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedulespringdatajpa.dto.schedule.ScheduleRequest;
import org.example.schedulespringdatajpa.dto.schedule.ScheduleResponse;
import org.example.schedulespringdatajpa.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // ✅ 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponse> create(@Valid @RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.create(request));
    }

    // ✅ 일정 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    // ✅ 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> update(@PathVariable Long id,
                                                   @Valid @RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.update(id, request));
    }

    // ✅ 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
