package com.nora_s.pmt_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nora_s.pmt_backend.entity.Project;
import com.nora_s.pmt_backend.entity.Task;
import com.nora_s.pmt_backend.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject(Project project);

    List<Task> findByProjectAndStatus(Project project, TaskStatus status);

    List<Task> findByProjectId(Long projectId);

    List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status);
}

