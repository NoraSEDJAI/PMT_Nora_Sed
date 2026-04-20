package com.nora_s.pmt_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nora_s.pmt_backend.entity.Task;
import com.nora_s.pmt_backend.entity.TaskHistory;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

    List<TaskHistory> findByTaskOrderByCreatedAtDesc(Task task);

}
