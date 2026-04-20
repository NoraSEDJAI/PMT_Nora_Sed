package com.nora_s.pmt_backend.dto;

import java.time.LocalDateTime;

public record UpdateTaskRequest(
        String title,
        String description,
        String status,
        String priority,
        LocalDateTime completedAt,
        Long actorId
) {}