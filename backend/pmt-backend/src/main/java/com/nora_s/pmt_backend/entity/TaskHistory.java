package com.nora_s.pmt_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_history")
public class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Tâche concernée
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    // 🔗 Utilisateur ayant effectué l’action
    @ManyToOne
    @JoinColumn(name = "performed_by", nullable = false)
    private User performedBy;

    /**
     * Champ de la task modifié
     * Exemples :
     * title, description, due_date, priority, status, assigned_to
     */
    @Column(name = "field_name", nullable = false)
    private String fieldName;

    // 🔁 Ancienne valeur
    @Column(name = "old_value", columnDefinition = "TEXT")
    private String oldValue;

    // 🔁 Nouvelle valeur
    @Column(name = "new_value", columnDefinition = "TEXT")
    private String newValue;

    // 🕒 Date de modification
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // =====================
    // CONSTRUCTEURS
    // =====================

    // 🔹 Constructeur vide (obligatoire JPA)
    // public TaskHistory(Task updatedTask, User actor, String par) {
    //     // this.createdAt = LocalDateTime.now();
    // }

    public TaskHistory(Task updatedTask, User actor, String par) {
    // obligatoire pour JPA
}


    // 🔹 Constructeur métier (recommandé)
    public TaskHistory(
            Task task,
            User performedBy,
            String fieldName,
            String oldValue,
            String newValue
    ) {
        this.task = task;
        this.performedBy = performedBy;
        this.fieldName = fieldName;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.createdAt = LocalDateTime.now();
    }

    // =====================
    // GETTERS
    // =====================

    public Long getId() {
        return id;
    }

    public Task getTask() {
        return task;
    }

    public User getPerformedBy() {
        return performedBy;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // =====================
    // SETTERS
    // =====================

    public void setTask(Task task) {
        this.task = task;
    }

    public void setPerformedBy(User performedBy) {
        this.performedBy = performedBy;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
