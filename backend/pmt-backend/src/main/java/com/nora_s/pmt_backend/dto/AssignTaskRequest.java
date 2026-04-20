package com.nora_s.pmt_backend.dto;

public record AssignTaskRequest(
        Long assignedUserId,
        Long actorId
) {}
