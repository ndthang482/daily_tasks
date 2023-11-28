package com.game.daily_tasks.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyTaskStatus {
    private LocalDate dateCurrent;
    private boolean marked;
}
