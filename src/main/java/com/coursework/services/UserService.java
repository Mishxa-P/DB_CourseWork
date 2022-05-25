package com.coursework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class UserService
{
    @Autowired
    DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void guestRegistration(String login, String password, String surname, String name, String patronymic, String phone_number, String passport, long date_of_birth ) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        System.out.println(password);
        String call = "{call Guest_registration(?,?,?,?,?,?,?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setString(1,login);
            statement.setString(2,passwordEncoder.encode(password));
            statement.setString(3,surname);
            statement.setString(4,name);
            statement.setString(5,patronymic);
            statement.setString(6,phone_number);
            statement.setString(7,passport);
            statement.setDate(8, new Date(date_of_birth));
            statement.execute();
        }
        connection.close();
    }

    public boolean checkData(String login, String password, String surname, String name, String patronymic, String phone_number, String passport)
    {
        if(login.equals(""))
        {
            return false;
        }
        if(password.equals(""))
        {
            return false;
        }
        if(surname.equals(""))
        {
            return false;
        }
        if(name.equals(""))
        {
            return false;
        }
        if(patronymic.equals(""))
        {
            return false;
        }
        if(phone_number.equals(""))
        {
            return false;
        }
        if(passport.equals(""))
        {
            return false;
        }
        return true;
    }

    public boolean checkUserLogin(String login) throws SQLException
    {
        if (login.length()<3 || login.length()>30)
        {
            return true;
        }
        Connection connection = null;

        connection = dataSource.getConnection();

        int exist = 0;


        String call = "{call Check_login(?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setString(1,login);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                exist = resultSet.getInt("exist");
            }
        }
        connection.close();

        if(exist==1)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
    public boolean checkUserPhone(String phone) throws SQLException
    {
        if(phone.length()>16)
        {
            return true;
        }
        Connection connection = null;

        connection = dataSource.getConnection();

        int exist = 0;


        String call = "{call Check_phone(?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setString(1,phone);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                exist = resultSet.getInt("exist");
            }
        }
        connection.close();

        if(exist==1)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }

    public boolean checkUserPassport(String passport) throws SQLException
    {
        if(passport.length()!=10)
        {
            return true;
        }
        Connection connection = null;

        connection = dataSource.getConnection();

        int exist = 0;


        String call = "{call Check_passport(?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setString(1,passport);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                exist = resultSet.getInt("exist");
            }
        }
        connection.close();

        if(exist==1)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }

}
