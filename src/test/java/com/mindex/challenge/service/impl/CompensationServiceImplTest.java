package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;
    private String compensationIdUrl;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("ABCD123");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Mindex");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Software Artist");

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary("200000");
        try {
            testCompensation.setEffectiveDate("04/01/2018");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, testEmployee.getEmployeeId()).getBody();
        assertEquals(testCompensation.getSalary(), readCompensation.getSalary());
        assertEquals(testCompensation.getEffectiveDate(), readCompensation.getEffectiveDate());
    }
}
