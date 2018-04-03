package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {

    @Autowired
    private ReportingStructureService reportingService;

    @GetMapping("reportingStructure/{id}")
    public ReportingStructure fetchReportingStructure(@PathVariable String id){
        return reportingService.fetchReportingStructure(id);
    }
}
