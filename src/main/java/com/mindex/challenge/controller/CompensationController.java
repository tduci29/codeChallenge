package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        return compensationService.createCompensation(compensation);
    }

    @GetMapping("/compensation/employee/{id}")
    public Compensation retrieveCompensation(@PathVariable String id) {
        return compensationService.retrieveCompensation(id);
    }
}
