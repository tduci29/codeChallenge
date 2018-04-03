package com.mindex.challenge.data;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Compensation {

    private Employee employee;
    private String salary;
    private String effectiveDate;

    public Compensation(){ }

    public void setEmployee(Employee employee){
	this.employee=employee;
    }

    public Employee getEmployee(){
	return employee;
    }

    public void setSalary(String salary){
        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal formatSalary = validator.validate(salary, Locale.US);
        if (formatSalary == null) {
            throw new RuntimeException("Not a valid currency: " + salary);
        }
        this.salary=formatSalary.toString();
    }

    public String getSalary(){
	return salary;
    }

    public void setEffectiveDate(String effectiveDate) throws Exception{
        if(validateDate(effectiveDate))
	        this.effectiveDate=effectiveDate;
        else
            throw new RuntimeException("Not a valid date: " + effectiveDate);
    }

    public String getEffectiveDate(){
	return effectiveDate;
    }

    protected boolean validateDate(String strDate)
    {
        if (strDate.trim().equals(""))
        {
            return true;
        }
        else
        {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
            sdfrmt.setLenient(false);
            try
            {
                Date javaDate = sdfrmt.parse(strDate);
            }
            catch (ParseException e)
            {
                return false;
            }
            return true;
        }
    }
}
