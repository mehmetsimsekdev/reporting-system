package com.project.ozguryazilim.responses;

import com.project.ozguryazilim.entities.Report;

import lombok.Data;

@Data
public class ReportResponse {
    Long id;
	Long userId;
	String userName;
	Long patienceId; // change to patient
    String patientName;
    String diseaseTitle;
    String diseaseDefinition;
    String reportDate;

    public ReportResponse(Report entity) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUserName();
		this.patienceId = entity.getPatienceId();
		this.patientName = entity.getPatientName();
		this.diseaseTitle = entity.getDiseaseTitle();
        this.diseaseDefinition = entity.getDiseaseDefinition();
        this.reportDate= entity.getReportDate();
    }

}
