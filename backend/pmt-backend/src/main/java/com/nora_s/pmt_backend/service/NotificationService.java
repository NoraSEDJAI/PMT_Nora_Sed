package com.nora_s.pmt_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nora_s.pmt_backend.entity.Notification;
import com.nora_s.pmt_backend.entity.Task;
import com.nora_s.pmt_backend.entity.User;
import com.nora_s.pmt_backend.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    public void notifyTaskAssigned(Task task, User user) {

        String message = "Une nouvelle tâche vous a été assignée : " + task.getTitle();

        // 1️⃣ Sauvegarde en base
        Notification notification = new Notification(user, message);
        notificationRepository.save(notification);

        // 2️⃣ Envoi email
        emailService.sendEmail(
                user.getEmail(),
                "Nouvelle tâche assignée",
                message
        );
    }
}
