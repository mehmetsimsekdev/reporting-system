package com.project.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.ozguryazilim.entities.Report;
import com.project.ozguryazilim.repos.ReportRepository;
import com.project.ozguryazilim.requests.ReportCreateRequest;
import com.project.ozguryazilim.services.ReportService;
import com.project.ozguryazilim.services.UserService;

@ExtendWith(MockitoExtension.class)
public class ReportServiseTest {
    

     @Mock   
     ReportRepository MockreportRepository;
     @Mock
     UserService MockuserService;
    
     @InjectMocks
     ReportService reportServiceUnderTest;

     @Test
     void getAllreports_shouldReturnAll() {
        Report report = new Report();
        report.setId((long) 23131231);
        report.setPatientName("assasfdsfvds");
        List<Report> reportList = Collections.singletonList(report);

        when(MockreportRepository.findAll()).thenReturn(reportList);


        List<Report> actual = reportServiceUnderTest.getAllreports(java.util.Optional.empty());

        assertEquals(report.getId(), actual.get(0).getId());
        assertEquals(report.getPatientName(), actual.get(0).getPatientName());



     }

     @Test
     void deleteById_itShouldDeleteFromRepo(){
        when(MockreportRepository.findById(anyLong())).thenReturn(Optional.empty());

        reportServiceUnderTest .deleteOneReport(1L);

        assertEquals(Optional.empty(),MockreportRepository.findById(1L));



     }

}
