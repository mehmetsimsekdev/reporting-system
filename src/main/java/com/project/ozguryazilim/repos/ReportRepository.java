package com.project.ozguryazilim.repos;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ozguryazilim.entities.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{

    List<Report> findByUserId(Long userId);

    Collection<? extends Report> findByPatientName(String keyword);

    Collection<? extends Report> findByDiseaseTitle(String keyword);

    Collection<? extends Report> findByReportDate(String keyword);

    Collection<? extends Report> findByPatienceId(Long longKeyword);

    Optional<Report> findById(Long longKeyword);



    



    
}
