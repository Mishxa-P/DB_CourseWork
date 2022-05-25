package com.coursework.services;

import com.coursework.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.sql.*;

import java.util.ArrayList;


@Component
public class AdminService
{
    @Autowired
    DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin getInfo(int adminId) throws SQLException
    {
        Admin admin = new Admin();
        Connection connection = null;
        Statement statement = null;

        connection = dataSource.getConnection();

        statement = connection.createStatement();

        String SQL = "Select * FROM Admins Where AdminId = " + adminId;

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            admin.setUserId(resultSet.getInt("UserId"));
            admin.setSurname(resultSet.getString("Surname"));
            admin.setName(resultSet.getString("Name"));
            admin.setPatronymic(resultSet.getString("Patronymic"));
            admin.setPhoneNumber(resultSet.getString("PhoneNumber"));

        }
        resultSet.close();
        statement.close();
        connection.close();
        return admin;
    }

    public int getAdminId(String username) throws SQLException
    {
        int adminId = 0;

        Connection connection = dataSource.getConnection();

        String call = "{call Get_admin_id(?)}";

        try(CallableStatement statement = connection.prepareCall(call))
        {
            statement.setString(1, username);

            ResultSet resultSet =  statement.executeQuery();
            while(resultSet.next())
            {
                adminId = resultSet.getInt("AdminId");
            }
            connection.close();
            return adminId;
        }
    }

    public ArrayList<Admin> getAllAdmins() throws SQLException
    {
        ArrayList<Admin> admins = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        connection = dataSource.getConnection();

        statement = connection.createStatement();

        String SQL = "Select * FROM Admins";

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            Admin admin = new Admin();
            admin.setAdminId(resultSet.getInt("AdminId"));
            admin.setUserId(resultSet.getInt("UserId"));
            admin.setSurname(resultSet.getString("Surname"));
            admin.setName(resultSet.getString("Name"));
            admin.setPatronymic(resultSet.getString("Patronymic"));
            admin.setPhoneNumber(resultSet.getString("PhoneNumber"));
            admins.add(admin);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return admins;
    }

    public ArrayList<Manager> getAllManagers() throws SQLException
    {
        ArrayList<Manager> managers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        connection = dataSource.getConnection();


        statement = connection.createStatement();

        String SQL = "Select * FROM Managers";

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            Manager manager = new Manager();
            manager.setManagerId(resultSet.getInt("ManagerId"));
            manager.setUserId(resultSet.getInt("UserId"));
            manager.setSurname(resultSet.getString("Surname"));
            manager.setName(resultSet.getString("Name"));
            manager.setPatronymic(resultSet.getString("Patronymic"));
            manager.setPhoneNumber(resultSet.getString("PhoneNumber"));
            managers.add(manager);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return managers;
    }

    public void deleteManager(int managerId) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Delete_manager(?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setInt(1, managerId);
            statement.execute();
        }
        connection.close();
    }

    public ArrayList<Guest> getAllGuests() throws SQLException
    {
        ArrayList<Guest> guests = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        connection = dataSource.getConnection();


        statement = connection.createStatement();

        String SQL = "Select * FROM Guests";

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            Guest guest = new Guest();
            guest.setGuestId(resultSet.getInt("GuestId"));
            guest.setUserId(resultSet.getInt("UserId"));
            guest.setSurname(resultSet.getString("Surname"));
            guest.setName(resultSet.getString("Name"));
            guest.setPatronymic(resultSet.getString("Patronymic"));
            guest.setPhoneNumber(resultSet.getString("PhoneNumber"));
            guest.setPassport(resultSet.getString("Passport"));
            guest.setDateOfBirth(resultSet.getDate("DateOfBirth"));


            guests.add(guest);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return guests;
    }

    public void changeGuestInfo(int guestId, String surname, String name, String patronymic, String phone_number, String passport, long date_of_birth ) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Change_guest_info(?,?,?,?,?,?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setInt(1, guestId);
            statement.setString(2,surname);
            statement.setString(3,name);
            statement.setString(4,patronymic);
            statement.setString(5,phone_number);
            statement.setString(6,passport);
            statement.setDate(7, new Date(date_of_birth));
            statement.execute();
        }
        connection.close();
    }

    public void changeManagerInfo(int managerId, String surname, String name, String patronymic, String phone_number) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Change_manager_info(?,?,?,?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setInt(1, managerId);
            statement.setString(2,surname);
            statement.setString(3,name);
            statement.setString(4,patronymic);
            statement.setString(5,phone_number);
            statement.execute();
        }
        connection.close();

    }


    public ArrayList<Room> getAllRooms() throws SQLException
    {
        ArrayList<Room> rooms = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        connection = dataSource.getConnection();

        statement = connection.createStatement();

        String SQL = "Select * FROM Rooms";

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            Room room = new Room();
            room.setRoomId(resultSet.getInt("RoomId"));
            room.setRoomNumber(resultSet.getInt("RoomNumber"));
            room.setCostOfLiving(resultSet.getDouble("CostOfLiving"));
            rooms.add(room);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return rooms;
    }

    public void addRoom(int roomNumber, double money) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Add_room(?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setInt(1, roomNumber);
            statement.setDouble(2, money);
            statement.execute();
        }
        connection.close();
    }

    public void deleteRoom(int roomId) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call  Delete_room(?)}";

        CallableStatement statement = connection.prepareCall(call);


        statement.setInt(1, roomId);
        statement.execute();

        connection.close();
    }

    public void changeRoom(int roomId,int roomNumber, double money) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call  Change_room(?,?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setInt(1, roomId);
            statement.setInt(2, roomNumber);
            statement.setDouble(3, money);
            statement.execute();
        }
        connection.close();
    }

    public ArrayList<Service> getAllServices() throws SQLException
    {
        ArrayList<Service> services = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        connection = dataSource.getConnection();

        statement = connection.createStatement();

        String SQL = "Select * FROM Services";

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            Service service = new Service();
            service.setServiceId(resultSet.getInt("ServiceId"));
            service.setServiceName(resultSet.getString("ServiceName"));
            service.setServiceCost(resultSet.getDouble("ServiceCost"));
            service.setDescription(resultSet.getString("Description"));
            services.add(service);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return services;
    }

    public void addService(String serviceName, double serviceCost, String description) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Add_service(?,?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setString(1, serviceName);
            statement.setDouble(2, serviceCost);
            statement.setString(3, description);
            statement.execute();
        }
        connection.close();
    }

    public void changeServiceCost(int serviceId, double serviceCost) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Change_service_cost(?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setInt(1, serviceId);
            statement.setDouble(2, serviceCost);
            statement.execute();
        }
        connection.close();
    }

    public void changeServiceDescription(int serviceId, String description) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Change_service_description(?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setInt(1, serviceId);
            statement.setString(2, description);
            statement.execute();
        }
        connection.close();
    }

    public void deleteService(int serviceId) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Delete_service(?)}";

        CallableStatement statement = connection.prepareCall(call);

        statement.setInt(1, serviceId);

        statement.execute();

        connection.close();
    }

    public ArrayList<Service> getPopularService() throws SQLException
    {
        ArrayList<Service> services = new ArrayList<>();
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Show_top_services()}";

        try(CallableStatement statement = connection.prepareCall(call))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Service service = new Service();
                service.setServiceName(resultSet.getString("ServiceName"));
                service.setServiceCost(resultSet.getDouble("ServiceCost"));
                services.add(service);
            }
            connection.close();
            return services;
        }
    }

    public double getTotalProfit(long startDate, long endDate) throws SQLException
    {
        double profit = 0;
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Count_total_profit(?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))
        {
            statement.setDate(1, new Date(startDate));
            statement.setDate(2, new Date(endDate));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                profit = resultSet.getInt(1);
            }
            connection.close();
            return profit;
        }
    }

    public ArrayList<Service> getUnpopularServices() throws SQLException
    {
        ArrayList<Service> services = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        connection = dataSource.getConnection();

        statement = connection.createStatement();

        String SQL = "Select ServiceId, ServiceName, ServiceCost, Description FROM Services Where ServiceId IN (SELECT m.service FROM(Select ServiceId as service, COUNT(ServiceId) as SelectedNumber \n" +
                "FROM RenderedServices Group By ServiceId HAVING (COUNT(ServiceId))= \n" +
                "(SELECT min(t.count) FROM( Select ServiceId as service, COUNT(ServiceId) as count FROM RenderedServices Group By ServiceId) as t)) as m)";

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            Service service = new Service();
            service.setServiceId(resultSet.getInt("ServiceId"));
            service.setServiceName(resultSet.getString("ServiceName"));
            service.setServiceCost(resultSet.getDouble("ServiceCost"));
            service.setDescription(resultSet.getString("Description"));
            services.add(service);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return services;
    }

    public ArrayList<Room> getPopularRooms() throws SQLException
    {
        ArrayList<Room> rooms = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        connection = dataSource.getConnection();

        statement = connection.createStatement();

        String SQL = "Select RoomId, RoomNumber, CostOfLiving FROM Rooms Where RoomId IN (SELECT m.room FROM(Select RoomId as room, COUNT(RoomId) as SelectedNumber \n" +
                "FROM Reservations Group By RoomId HAVING (COUNT(RoomId)) = \n" +
                "(SELECT max(t.count) FROM( Select RoomId as room, COUNT(RoomId) as count FROM Reservations Group By RoomId) as t)) as m)";

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            Room room = new Room();
            room.setRoomId(resultSet.getInt("RoomId"));
            room.setRoomNumber(resultSet.getInt("RoomNumber"));
            room.setCostOfLiving(resultSet.getDouble("CostOfLiving"));
            rooms.add(room);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return rooms;
    }

    public void registerManager(String login, String password, String surname, String name, String patronymic, String phone_number) throws SQLException
    {
        Connection connection = null;

        connection = dataSource.getConnection();

        String call = "{call Manager_registration(?,?,?,?,?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setString(1,login);
            statement.setString(2,passwordEncoder.encode(password));
            statement.setString(3,surname);
            statement.setString(4,name);
            statement.setString(5,patronymic);
            statement.setString(6,phone_number);
            statement.execute();
        }
        connection.close();
    }

    public boolean checkData(String login, String password, String surname, String name, String patronymic, String phone_number)
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

        return true;
    }

    public boolean checkManagerLogin(String login) throws SQLException
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
    public boolean checkManagerPhone(String phone) throws SQLException
    {
        if(phone.length()>16)
        {
            return true;
        }
        Connection connection = null;

        connection = dataSource.getConnection();

        int exist = 0;


        String call = "{call Check_phone_manager(?)}";

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



}
