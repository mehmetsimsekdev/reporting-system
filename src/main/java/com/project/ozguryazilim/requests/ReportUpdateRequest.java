package com.project.ozguryazilim.requests;

import lombok.Data;

@Data
public class ReportUpdateRequest {

    Long patienceId; // change to patient
    String patientName;
    String diseaseTitle;
    String diseaseDefinition;
    String reportDate;

}
