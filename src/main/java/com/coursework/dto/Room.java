package com.coursework.dto;

public class Room
{
    private int roomId;
    private int roomNumber;
    private double costOfLiving;

    public Room()
    {

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

    @Override
    public String toString()
    {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber=" + roomNumber +
                ", costOfLiving=" + costOfLiving +
                '}';
    }
}
