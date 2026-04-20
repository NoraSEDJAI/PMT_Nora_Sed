package com.nora_s.pmt_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nora_s.pmt_backend.entity.Notification;
import com.nora_s.pmt_backend.entity.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUser(User user);
}