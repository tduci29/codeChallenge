package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompensationServiceImpl implements CompensationService {

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation createCompensation(Compensation compensation) {
        Compensation exists = compensationRepository.findCompensationByEmployeeEmployeeId(compensation.getEmployee().getEmployeeId());
        if( null == exists )
        {
            compensationRepository.insert(compensation);
        }
        else
        {
            throw new RuntimeException("Compensation record already exists for this employee: " + exists.getEmployee().getEmployeeId());
        }
        return compensation;
    }

    @Override
    public Compensation retrieveCompensation(String id) {
    	Compensation ret=null;
        ret=compensationRepository.findCompensationByEmployeeEmployeeId(id);

        if (ret == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }	
	
        return ret;
    }
}