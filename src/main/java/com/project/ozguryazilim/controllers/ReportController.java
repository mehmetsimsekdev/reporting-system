package com.project.ozguryazilim.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.ozguryazilim.entities.Report;
import com.project.ozguryazilim.requests.ReportCreateRequest;
import com.project.ozguryazilim.requests.ReportUpdateRequest;
import com.project.ozguryazilim.services.ReportService;


@RestController
@RequestMapping
public class ReportController {
    private  ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    
    @GetMapping("/reports")
    public ModelAndView getAllreports(@RequestParam Optional<Long> userId,Model model){
        List<Report> allReports = reportService.getAllreports(userId);
        model.addAttribute("reports",allReports);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("report/listReports");
        return modelAndView;
    } 

    @GetMapping("/reportCreate")
    public ModelAndView createOneReportPage(@ModelAttribute("newReport") ReportCreateRequest newReportRequest){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("report/createReport");
        return modelAndView;
    }
    @PostMapping("/reportCreate")
    public void createOneReport(@ModelAttribute("newReport") ReportCreateRequest newReportRequest,HttpServletRequest request,HttpServletResponse response) throws IOException{
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if( cookies != null ) {
            for (int i = 0; i < cookies.length; i++) {
               cookie = cookies[i];
               if(cookie.getName().equals("id")){
                    newReportRequest.setUserId((Long.parseLong(cookie.getValue())));    
                
                                    
                } 
            }
        }
        Report reportTmp = reportService.createOneReport(newReportRequest);
        response.sendRedirect("/report/"+reportTmp.getId());
    }

    @GetMapping("report/{reportId}")
    public ModelAndView getOneReport(@PathVariable Long reportId,Model model){
        Report report = reportService.getOneReportById(reportId);
        model.addAttribute("report", report);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("report/reportDesign");
        return modelAndView;
    }

    @GetMapping(value="reportEdit/{reportId}")
    public ModelAndView updateOneReportPage(@PathVariable Long reportId, @ModelAttribute("updatedReport") ReportUpdateRequest updateReport,Model model){
        Report report = reportService.getOneReportById(reportId);
        model.addAttribute("oldReport", report);
        model.addAttribute("oldReportId",reportId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("report/editReport");
        return modelAndView;
    }
    
   
    @PostMapping(value="reportEdit/{reportId}")
    public void updateOneReport(@PathVariable Long reportId, @ModelAttribute("updatedReport") ReportUpdateRequest updateReport,HttpServletResponse response)throws IOException{
        Report newReport =reportService.updateOneReport(reportId,updateReport);
        response.sendRedirect("/report/"+reportId);
    }
    

    @PostMapping("reportDelete/{reportId}")
    public void deleteOneReport(@PathVariable Long reportId,HttpServletResponse response) throws IOException{
        reportService.deleteOneReport(reportId);
        response.sendRedirect("/reports");

    }
}
