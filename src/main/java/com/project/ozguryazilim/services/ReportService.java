package com.project.ozguryazilim.services;

import java.util.List;
import java.util.Optional;

import javax.print.attribute.SetOfIntegerSyntax;

import org.springframework.stereotype.Service;


import com.project.ozguryazilim.entities.Report;
import com.project.ozguryazilim.entities.User;
import com.project.ozguryazilim.repos.ReportRepository;
import com.project.ozguryazilim.requests.ReportCreateRequest;
import com.project.ozguryazilim.requests.ReportUpdateRequest;
import com.project.ozguryazilim.responses.ReportResponse;



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
        toSave.setUser(user);
        System.out.print(toSave.getDiseaseDefinition());
        System.out.print(toSave.getPatienceId());
        System.out.println(toSave.getPatienceId().getClass());
        System.out.println(toSave.getPatienceId().getClass().getName());


        
        return reportRepository.save(toSave);

    }

    public Report updateOneReport(Long reportId, ReportUpdateRequest updateReport) {
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isPresent()){
            Report toUpdate = new Report();
            toUpdate = report.get();
            toUpdate.setDiseaseTitle(updateReport.getDiseaseTitle());
            toUpdate.setDiseaseDefinition(updateReport.getDiseaseDefinition());
            reportRepository.save(toUpdate);
            return toUpdate;
            
        }
        
        return null;
    }

    public void deleteOneReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }
    
}
