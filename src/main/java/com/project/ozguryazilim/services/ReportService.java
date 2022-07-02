package com.project.ozguryazilim.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.project.ozguryazilim.entities.Report;
import com.project.ozguryazilim.entities.User;
import com.project.ozguryazilim.repos.ReportRepository;
import com.project.ozguryazilim.requests.ReportCreateRequest;



@Service

public class ReportService {
    private ReportRepository reportRepository;
    private UserService userService;

    public ReportService(ReportRepository postRepository, UserService userService) {
        this.reportRepository = postRepository;
        this.userService = userService;
    }

    public List<Report> getAllreports(Optional<Long> userId) {
        if(userId.isPresent())
            return reportRepository.findByUserId(userId.get());
        return reportRepository.findAll();
    } 

    public Report getOneReportById(Long reportId) {
        return reportRepository.findById(reportId).orElse(null);

    }

    public Report createOneReport(ReportCreateRequest newReportRequest) {
        User user = userService.getOneUser(newReportRequest.getUserId());
        if (user == null)
            return null;
        Report toSave = new Report();
        toSave.setId(newReportRequest.getId());
        toSave.setDiseaseDefinition(newReportRequest.getDiseaseDefinition());
        toSave.setDiseaseTitle(newReportRequest.getDiseaseTitle());
        toSave.setPatienceId(newReportRequest.getPatienceId());
        toSave.setPatientName(newReportRequest.getPatientName());
        toSave.setReportDate(newReportRequest.getReportDate());
        toSave.setReportId(newReportRequest.getReportId());
        toSave.setUser(user);
        return reportRepository.save(toSave);

    }
    
}
