package com.game.daily_tasks.repository;

import com.game.daily_tasks.domain.entity.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {
    List<DailyTask> findAllByUserId(Long userId);

    boolean existsByUserIdAndDate(Long userId, LocalDate date);

    List<DailyTask> findByUserIdAndDateBetween(Long userId, LocalDate firstDayOfMonth, LocalDate lastDayOfMonth);
}
