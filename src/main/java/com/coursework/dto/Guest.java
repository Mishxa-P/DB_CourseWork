package com.coursework.dto;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Guest
{
    private int guestId;
    private int userId;

    private String surname;

    private String name;

    private String patronymic;

    private String phoneNumber;

    private String passport;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    public Guest()
    {

    }

    public int getGuestId()
    {
        return guestId;
    }

    public void setGuestId(int guestId)
    {
        this.guestId = guestId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
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

    public String getPatronymic()
    {
        return patronymic;
    }

    public void setPatronymic(String patronymic)
    {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPassport()
    {
        return passport;
    }

    public void setPassport(String passport)
    {
        this.passport = passport;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString()
    {
       /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(dateOfBirth);*/


        return "Guest{" +
                "guestId=" + guestId +
                ", userId=" + userId +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passport='" + passport + '\'' +
                ", dateOfBirth=" +  dateOfBirth +
                '}';
    }
}
