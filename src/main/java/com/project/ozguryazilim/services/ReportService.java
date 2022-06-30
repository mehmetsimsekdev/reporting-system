package com.project.ozguryazilim.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.project.ozguryazilim.entities.Report;
import com.project.ozguryazilim.repos.ReportRepository;

@Service
public class ReportService {
    private ReportRepository reportRepository;

    public ReportService(ReportRepository postRepository) {
        this.reportRepository = postRepository;
    }

   /* *public List<Report> getAllreports(Optional<Long> userId) {
        if(userId.isPresent())
            return reportRepository.findByPatienceID(userId.get());
        return reportRepository.findAll();
    } */

    public Report getOneReportById(Long reportId) {
        return reportRepository.findById(reportId).orElse(null);

    }

    public Report createOneReport(Report newReport) {
        return reportRepository.save(newReport);
    }
    
}
