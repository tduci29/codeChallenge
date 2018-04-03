package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.data.ReportingStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure fetchReportingStructure(String EmployeeId){

    ReportingStructure reportingStructure;

    Employee employee=employeeRepository.findByEmployeeId(EmployeeId);

	if (employee == null)
	{
    	throw new RuntimeException("Invalid employeeId: " + EmployeeId);
    }
	else
	{
		reportingStructure=new ReportingStructure();
		reportingStructure.setEmployee(employee);
	    countReports(employee.getDirectReports(), reportingStructure);
	}
		return reportingStructure;
    }

    //Count all Reports for a given employee.
    public void countReports(List<Employee> directReports, ReportingStructure reportingStructure)
	{
		if( null != directReports && directReports.size() > 0 ) {
			for (Employee emp : directReports) {
				countReports( employeeRepository.findByEmployeeId(emp.getEmployeeId()).getDirectReports(), reportingStructure);
				reportingStructure.setNumberOfReports(reportingStructure.getNumberOfReports() + 1);
			}
		}
	}
}
