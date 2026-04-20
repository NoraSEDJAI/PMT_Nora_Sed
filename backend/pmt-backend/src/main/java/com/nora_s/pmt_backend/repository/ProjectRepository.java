package com.nora_s.pmt_backend.repository;

import com.nora_s.pmt_backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
