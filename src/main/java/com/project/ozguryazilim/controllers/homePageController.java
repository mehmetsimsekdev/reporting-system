package com.project.ozguryazilim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homePageController {
    
    @GetMapping("/")
    public String signIn(){return "report/listReports";}

}

