package com.project.ozguryazilim.repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ozguryazilim.entities.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{

// List<Report> findByPatienceID(Long patienceId);

    
}
