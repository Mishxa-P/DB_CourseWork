package com.coursework.dto;

public class Service
{
    private int serviceId;
    private String serviceName;
    private double serviceCost;
    private String description;

    public Service()
    {

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

    public double getServiceCost()
    {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost)
    {
        this.serviceCost = serviceCost;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return serviceName;
    }
}
