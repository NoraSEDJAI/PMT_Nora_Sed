package com.nora_s.pmt_backend.dto;

import java.time.LocalDateTime;

public record CreateTaskRequest(
        Long projectId,
        String title,
        String description,
        String priority,
        LocalDateTime dueDate
) {}


