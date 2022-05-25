package com.coursework.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DressedReservation
{


    private String surname;
    private String name;
    private String phoneNumber;
    private int roomId;
    private int reservationId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDayOfReservation;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDayOfReservation;

    private double totalPayment;

    public DressedReservation()
    {

    }

    public int getReservationId()
    {
        return reservationId;
    }

    public void setReservationId(int reservationId)
    {
        this.reservationId = reservationId;
    }

    public int getRoomId()
    {
        return roomId;
    }

    public void setRoomId(int roomId)
    {
        this.roomId = roomId;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Date getStartDayOfReservation()
    {
        return startDayOfReservation;
    }

    public void setStartDayOfReservation(Date startDayOfReservation)
    {
        this.startDayOfReservation = startDayOfReservation;
    }

    public Date getEndDayOfReservation()
    {
        return endDayOfReservation;
    }

    public void setEndDayOfReservation(Date endDayOfReservation)
    {
        this.endDayOfReservation = endDayOfReservation;
    }

    public double getTotalPayment()
    {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment)
    {
        this.totalPayment = totalPayment;
    }
}
