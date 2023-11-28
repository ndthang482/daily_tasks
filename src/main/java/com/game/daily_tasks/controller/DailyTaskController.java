package com.game.daily_tasks.controller;

import com.game.daily_tasks.common.HttpStatusCode;
import com.game.daily_tasks.domain.entity.DailyTask;
import com.game.daily_tasks.domain.model.DailyTaskRequest;
import com.game.daily_tasks.domain.model.MonthlyReport;
import com.game.daily_tasks.domain.message.BaseMessage;
import com.game.daily_tasks.domain.message.ExtendedMessage;
import com.game.daily_tasks.service.impl.DailyTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller class for managing daily tasks API.
 * Endpoints include marking a day, retrieving history for all users, and retrieving history for a specific user.
 */
@Tag(name = "Daily Task Management API", description = "Endpoints for managing daily tasks")
@RestController
@RequestMapping("/daily-task")
@RequiredArgsConstructor
public class DailyTaskController extends BaseController{

    private final DailyTaskService dailyTaskService;

    /**
     * Marks the specified day for a user and adds points if not already marked
     *
     * @param request The request containing userId and date
     */
    @Operation(summary = "Marks the specified day for a user and adds points if not already marked")
    @ApiResponse(responseCode = HttpStatusCode.OK, description = "Day marked successfully",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class)) })
    @ApiResponse(responseCode = HttpStatusCode.BAD_REQUEST, description = "Invalid input",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BaseMessage.class)) })
    @ApiResponse(responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR, description = "Internal Server Error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BaseMessage.class)) })
    @PostMapping("/mark-day")
    public ResponseEntity<?> markDay(@RequestBody DailyTaskRequest request) {
        dailyTaskService.markDay(request.getUserId(), request.getDate());
        return successResponse("Day marked successfully");
    }

    /**
     * Retrieve the entire daily reward history of all users
     *
     * @return A list of DailyTask objects representing the history
     */
    @Operation(summary = "Retrieve the entire daily reward history of all users")
        @ApiResponse(responseCode = HttpStatusCode.OK, description = "History retrieved successfully",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class)) })
    @ApiResponse(responseCode = HttpStatusCode.BAD_REQUEST, description = "Invalid input",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BaseMessage.class)) })
    @ApiResponse(responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR, description = "Internal Server Error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BaseMessage.class)) })
    @GetMapping("/history")
    public List<DailyTask> getHistory() {
        return dailyTaskService.findAll();
    }

    /**
     * Retrieves the monthly report for a specific user
     *
     * @param userId The unique identifier of the user
     * @return A list of DailyTask objects representing the user's history
     */
    @Operation(summary = "Retrieves the monthly mark for a specific user")
    @ApiResponse(responseCode = HttpStatusCode.OK, description = "User history retrieved successfully",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class)) })
    @ApiResponse(responseCode = HttpStatusCode.BAD_REQUEST, description = "Invalid input",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BaseMessage.class)) })
    @ApiResponse(responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR, description = "Internal Server Error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BaseMessage.class)) })
    @GetMapping("/user/history/{userId}")
    public List<DailyTask> getHistoryUser(@PathVariable Long userId) {
        return dailyTaskService.findAllByUserId(userId);
    }

    /**
     * Retrieves the monthly mark for a specific user
     *
     * @param userId The unique identifier of the user
     * @return The MonthlyReport object representing the user's monthly report
     */
    @Operation(summary = "Retrieves the monthly mark for a specific user")
    @ApiResponse(responseCode = HttpStatusCode.OK, description = "Mark to receive monthly rewards",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class)) })
    @ApiResponse(responseCode = HttpStatusCode.BAD_REQUEST, description = "Invalid input",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BaseMessage.class)) })
    @ApiResponse(responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR, description = "Internal Server Error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BaseMessage.class)) })
    @GetMapping("/monthly-mark/{userId}")
    public MonthlyReport getMonthlyReport(@PathVariable Long userId) {
        return dailyTaskService.generateMonthlyReport(userId);
    }
}
