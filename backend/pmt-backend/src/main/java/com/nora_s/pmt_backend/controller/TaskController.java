package com.nora_s.pmt_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nora_s.pmt_backend.dto.CreateTaskRequest;
import com.nora_s.pmt_backend.dto.TaskDashboardDTO;
import com.nora_s.pmt_backend.entity.Task;
import com.nora_s.pmt_backend.entity.TaskHistory;
import com.nora_s.pmt_backend.entity.TaskStatus;
import com.nora_s.pmt_backend.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ==========================
    // CREATE TASK (JWT)
    // ==========================
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest request) {

        Task task = taskService.createTask(
                request.projectId(),
                request.title(),
                request.description(),
                request.priority(),
                request.dueDate()
        );

        return ResponseEntity.ok(task);
    }

    // ==========================
    // GET TASK BY ID
    // ==========================
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    // ==========================
    // GET TASKS BY PROJECT
    // ==========================
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProject(projectId));
    }

    // ==========================
    // GET TASK HISTORY
    // ==========================
    @GetMapping("/{taskId}/history")
    public ResponseEntity<List<TaskHistory>> getTaskHistory(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTaskHistory(taskId));
    }

    // ==========================
    // GET TASKS BY STATUS
    // ==========================
    @GetMapping("/project/{projectId}/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(
            @PathVariable Long projectId,
            @PathVariable TaskStatus status) {

        return ResponseEntity.ok(
                taskService.getTasksByProjectAndStatus(projectId, status)
        );
    }

    // ==========================
    // DASHBOARD
    // ==========================
    @GetMapping("/dashboard")
    public ResponseEntity<TaskDashboardDTO> getDashboard(
            @RequestParam Long projectId) {

        return ResponseEntity.ok(taskService.getDashboard(projectId));
    }
}
