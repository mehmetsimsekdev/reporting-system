package com.project.ozguryazilim.requests;

import lombok.Data;

@Data
public class ReportCreateRequest {
    
    Long patienceId;
    String patientName;
    String diseaseTitle;
    String diseaseDefinition;
    String reportDate;
    Long userId;
    
    
    
}
