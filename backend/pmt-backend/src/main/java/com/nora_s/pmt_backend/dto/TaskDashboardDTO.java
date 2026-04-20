package com.nora_s.pmt_backend.dto;

import java.util.List;

import com.nora_s.pmt_backend.entity.Task;

public class TaskDashboardDTO {

    private final List<Task> todo;
    private final List<Task> inProgress;
    private final List<Task> done;

    public TaskDashboardDTO(List<Task> todo, List<Task> inProgress, List<Task> done) {
        this.todo = todo;
        this.inProgress = inProgress;
        this.done = done;
    }

    public List<Task> getTodo() {
        return todo;
    }

    public List<Task> getInProgress() {
        return inProgress;
    }

    public List<Task> getDone() {
        return done;
    }
}
