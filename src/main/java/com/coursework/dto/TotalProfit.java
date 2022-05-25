package com.coursework.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TotalProfit
{
    @DateTimeFormat(pattern="yyyy-MM-dd")
    Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    Date endDate;

    double profit;

    public TotalProfit()
    {

    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public double getProfit()
    {
        return profit;
    }

    public void setProfit(double profit)
    {
        this.profit = profit;
    }
}
