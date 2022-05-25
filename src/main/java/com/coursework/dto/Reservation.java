package com.coursework.dto;

import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

public class Reservation
{
    private int reservationId;
    private int roomId;
    private int guestId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDayOfReservation;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDayOfReservation;

    private double totalPayment;

    public Reservation()
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

    public int getGuestId()
    {
        return guestId;
    }

    public void setGuestId(int guestId)
    {
        this.guestId = guestId;
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

    @Override
    public String toString()
    {



        return "Reservation{" +
                "reservationId=" + reservationId +
                ", roomId=" + roomId +
                ", guestId=" + guestId +
                ", startDayOfReservation=" + startDayOfReservation +
                ", endDayOfReservation=" + endDayOfReservation +
                ", totalPayment=" + totalPayment +
                '}';
    }
}
