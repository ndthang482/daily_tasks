package com.game.daily_tasks.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReport {
    private String month;
    private List<DailyTaskStatus> dailyTasks;
}
