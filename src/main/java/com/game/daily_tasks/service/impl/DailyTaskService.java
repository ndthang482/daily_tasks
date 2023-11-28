package com.game.daily_tasks.service.impl;

import com.game.daily_tasks.domain.entity.DailyTask;
import com.game.daily_tasks.domain.model.DailyTaskStatus;
import com.game.daily_tasks.domain.model.MonthlyReport;
import com.game.daily_tasks.domain.entity.User;
import com.game.daily_tasks.repository.DailyTaskRepository;
import com.game.daily_tasks.repository.UserRepository;
import com.game.daily_tasks.service.IDailyTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing daily tasks.
 * Implements the IDailyTaskService interface.
 */
@Service
@RequiredArgsConstructor
public class DailyTaskService implements IDailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void markDay(Long userId, LocalDate date) {
        LocalDate today = LocalDate.now();
        // Check if the date is marked and is the current date
        if (!dailyTaskRepository.existsByUserIdAndDate(userId, date) && date.equals(today)) {
            //If it is the current date, mark it and add points
            DailyTask dailyTask = new DailyTask();
            dailyTask.setUserId(userId);
            dailyTask.setDate(date);
            dailyTask.setPoints(1000);
            dailyTaskRepository.save(dailyTask);

            //Add points to the user's score
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                user.setPoints(user.getPoints() + dailyTask.getPoints());
                userRepository.save(user);
            }
        }
    }

    @Override
    public List<DailyTask> findAllByUserId(Long userId) {
        return dailyTaskRepository.findAllByUserId(userId);
    }

    @Override
    public List<DailyTask> findAll() {
        return dailyTaskRepository.findAll();
    }

    @Override
    public MonthlyReport generateMonthlyReport(Long userId) {
        MonthlyReport monthlyReport = new MonthlyReport();
        YearMonth currentYearMonth = YearMonth.now();
        String month = currentYearMonth.toString();
        monthlyReport.setMonth(month);

        List<DailyTaskStatus> dailyTasks = new ArrayList<>();
        LocalDate lastDayOfMonth = currentYearMonth.atEndOfMonth();
        LocalDate startOfLast7Days = lastDayOfMonth.minusDays(6);

        // Get the user's attendance list for that month
        List<DailyTask> userDailyTasks = dailyTaskRepository.findByUserIdAndDateBetween(userId, startOfLast7Days, lastDayOfMonth);

        for (int i = 0; i < 7; i++) {
            LocalDate date = startOfLast7Days.plusDays(i);
            DailyTaskStatus taskStatus = new DailyTaskStatus();
            taskStatus.setDateCurrent(date);

            //Check if the date is within the 7 days displayed
            boolean marked = userDailyTasks.stream().anyMatch(task -> task.getDate().isEqual(date));
            taskStatus.setMarked(marked);

            dailyTasks.add(taskStatus);
        }

        monthlyReport.setDailyTasks(dailyTasks);

        return monthlyReport;
    }

}
