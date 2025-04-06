package org.example.schedulespringdatajpa.repository;

import org.example.schedulespringdatajpa.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
