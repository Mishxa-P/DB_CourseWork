package com.coursework.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RenderedService
{
    private int renderedServiceId;
    private int serviceId;
    private String serviceName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date orderDate;

    private double serviceCost;

    private int reservationId;

    public RenderedService()
    {

    }

    public int getRenderedServiceId()
    {
        return renderedServiceId;
    }

    public void setRenderedServiceId(int renderedServiceId)
    {
        this.renderedServiceId = renderedServiceId;
    }

    public int getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public int getReservationId()
    {
        return reservationId;
    }

    public void setReservationId(int reservationId)
    {
        this.reservationId = reservationId;
    }

    public double getServiceCost()
    {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost)
    {
        this.serviceCost = serviceCost;
    }

    @Override
    public String toString()
    {
        return "RenderedService{" +
                "renderedServiceId=" + renderedServiceId +
                ", serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", orderDate=" + orderDate +
                ", serviceCost=" + serviceCost +
                ", reservationId=" + reservationId +
                '}';
    }
}
