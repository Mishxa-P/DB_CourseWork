package com.coursework.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FreeRoom
{

    @DateTimeFormat(pattern="yyyy-MM-dd")
    Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    Date endDate;
    private int roomId;
    private int roomNumber;
    private double costOfLiving;

    public FreeRoom()
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

    public int getRoomId()
    {
        return roomId;
    }

    public void setRoomId(int roomId)
    {
        this.roomId = roomId;
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public double getCostOfLiving()
    {
        return costOfLiving;
    }

    public void setCostOfLiving(double costOfLiving)
    {
        this.costOfLiving = costOfLiving;
    }
}
