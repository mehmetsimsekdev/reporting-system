package com.project.ozguryazilim.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ozguryazilim.entities.Report;
import com.project.ozguryazilim.services.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private  ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    
     @GetMapping
    public List<Report> getAllreports(@RequestParam Optional<Long> userId){
        return reportService.getAllreports(userId);
        
    }

    @GetMapping("/{reportId}")
    public Report getOneReport(@PathVariable Long reportId){
        return reportService.getOneReportById(reportId);
    }

    @PostMapping
    public Report createOneReport(@RequestBody Report newReport){
        return reportService.createOneReport(newReport);
    }
}
