package com.game.daily_tasks.service;

import com.game.daily_tasks.domain.entity.DailyTask;
import com.game.daily_tasks.domain.model.MonthlyReport;

import java.time.LocalDate;
import java.util.List;

public interface IDailyTaskService {
    List<DailyTask> findAll();
    void markDay(Long userId, LocalDate date);
    List<DailyTask> findAllByUserId(Long userId);
    MonthlyReport generateMonthlyReport(Long userId);
}
