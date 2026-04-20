package com.nora_s.pmt_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nora_s.pmt_backend.dto.TaskDashboardDTO;
import com.nora_s.pmt_backend.entity.Project;
import com.nora_s.pmt_backend.entity.Task;
import com.nora_s.pmt_backend.entity.TaskHistory;
import com.nora_s.pmt_backend.entity.TaskStatus;
import com.nora_s.pmt_backend.entity.User;
import com.nora_s.pmt_backend.repository.ProjectRepository;
import com.nora_s.pmt_backend.repository.TaskHistoryRepository;
import com.nora_s.pmt_backend.repository.TaskRepository;
import com.nora_s.pmt_backend.repository.UserRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskHistoryRepository taskHistoryRepository;
    private final EmailService emailService;

    public TaskService(
            TaskRepository taskRepository,
            ProjectRepository projectRepository,
            UserRepository userRepository,
            TaskHistoryRepository taskHistoryRepository,
            EmailService emailService
    ) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskHistoryRepository = taskHistoryRepository;
        this.emailService = emailService;
    }

    // ==========================
    // CREATE TASK (JWT)
    // ==========================
    public Task createTask(
        Long projectId,
        String title,
        String description,
        String priority,
        LocalDateTime dueDate
) {
    String email = (String) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

    User currentUser = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Projet introuvable"));

    Task task = new Task();
    task.setProject(project);
    task.setCreatedBy(currentUser);
    task.setTitle(title);
    task.setDescription(description);
    task.setPriority(priority);
    task.setDueDate(dueDate);
    task.setStatus(TaskStatus.TODO);

    Task savedTask = taskRepository.save(task);

    taskHistoryRepository.save(
            new TaskHistory(savedTask, currentUser, "Création de la tâche")
    );

    return savedTask;
}


    // ==========================
    // GETTERS
    // ==========================
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public List<Task> getTasksByProjectAndStatus(Long projectId, TaskStatus status) {
        return taskRepository.findByProjectIdAndStatus(projectId, status);
    }

    public List<TaskHistory> getTaskHistory(Long taskId) {
        Task task = getTaskById(taskId);
        return taskHistoryRepository.findByTaskOrderByCreatedAtDesc(task);
    }

    // ==========================
    // DASHBOARD
    // ==========================
    public TaskDashboardDTO getDashboard(Long projectId) {

        List<Task> todo =
                taskRepository.findByProjectIdAndStatus(projectId, TaskStatus.TODO);

        List<Task> inProgress =
                taskRepository.findByProjectIdAndStatus(projectId, TaskStatus.IN_PROGRESS);

        List<Task> done =
                taskRepository.findByProjectIdAndStatus(projectId, TaskStatus.DONE);

        return new TaskDashboardDTO(todo, inProgress, done);
    }
}
