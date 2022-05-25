package com.coursework.services;


import com.coursework.dto.Guest;
import com.coursework.dto.Reservation;
import com.coursework.dto.Room;
import com.coursework.dto.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class GuestService
{
    @Autowired
    DataSource dataSource;

    public Guest getInfo(int guestId) throws SQLException
    {
        Guest guest = new Guest();
        Connection connection = null;
        Statement statement = null;

        connection = dataSource.getConnection();

        statement = connection.createStatement();

        String SQL = "Select * FROM Guests Where GuestId = " + guestId;

        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next())
        {
            guest.setGuestId(resultSet.getInt("GuestId"));
            guest.setUserId(resultSet.getInt("UserId"));
            guest.setSurname(resultSet.getString("Surname"));
            guest.setName(resultSet.getString("Name"));
            guest.setPatronymic(resultSet.getString("Patronymic"));
            guest.setPhoneNumber(resultSet.getString("PhoneNumber"));
            guest.setPassport(resultSet.getString("Passport"));
            guest.setDateOfBirth(resultSet.getDate("DateOfBirth"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return guest;
    }

    public ArrayList<Room> getFreeRooms(long startDate, long endDate) throws SQLException
    {
        ArrayList<Room> rooms = new ArrayList<>();


        Connection connection = dataSource.getConnection();


        String call = "{call Show_free_rooms(?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))
        {
            statement.setDate(1, new Date(startDate));
            statement.setDate(2, new Date(endDate));

            ResultSet resultSet =  statement.executeQuery();
            while(resultSet.next())
            {
                Room room = new Room();
                room.setRoomId(resultSet.getInt("RoomId"));
                room.setRoomNumber(resultSet.getInt("RoomNumber"));
                room.setCostOfLiving(resultSet.getDouble("CostOfLiving"));
                rooms.add(room);
            }
            connection.close();
            return rooms;
        }

    }

    public int getGuestId(String username) throws SQLException
    {
        int guestId = 0;

        Connection connection = dataSource.getConnection();

        String call = "{call Get_guest_id(?)}";

        try(CallableStatement statement = connection.prepareCall(call))
        {
            statement.setString(1, username);

            ResultSet resultSet =  statement.executeQuery();
            while(resultSet.next())
            {
               guestId = resultSet.getInt("GuestId");
            }
            connection.close();
            return guestId;
        }
    }

    public void addReservation(int roomId, int guestId, long startDate, long endDate) throws SQLException
    {
        Connection   connection = dataSource.getConnection();

        String call = "{call Add_reservation(?,?,?,?)}";

       CallableStatement statement = connection.prepareCall(call);


            statement.setInt(1, roomId);
            statement.setInt(2, guestId);
            statement.setDate(3, new Date(startDate));
            statement.setDate(4, new Date(endDate));
            statement.execute();

        connection.close();

    }

    public ArrayList<Reservation> getGuestReservation(int guestId) throws SQLException
    {
        ArrayList<Reservation> reservations = new ArrayList<>();

        Connection connection = dataSource.getConnection();

        String call = "{call Show_all_guest_reservations(?)}";

        try(CallableStatement statement = connection.prepareCall(call))
        {
            statement.setInt(1, guestId);

            ResultSet resultSet =  statement.executeQuery();
            while(resultSet.next())
            {
                Reservation reservation = new Reservation();
                reservation.setReservationId(resultSet.getInt("ReservationId"));
                reservation.setRoomId(resultSet.getInt("Room"));
                reservation.setStartDayOfReservation(resultSet.getDate("StartDayOfReservation"));
                reservation.setEndDayOfReservation(resultSet.getDate("EndDayOfReservation"));
                reservation.setTotalPayment(resultSet.getDouble("TotalPayment"));
                reservations.add(reservation);
            }
            connection.close();
            return reservations;
        }
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

    public Reservation getReservationById(int reservationId) throws SQLException
    {
        Reservation reservation = new Reservation();

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        String SQL = "Select RoomId as Room, StartDayOfReservation, EndDayOfReservation, TotalPayment FROM Reservations Where ReservationId ="+ reservationId;

        ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next())
            {
                reservation.setRoomId(resultSet.getInt("Room"));
                reservation.setStartDayOfReservation(resultSet.getDate("StartDayOfReservation"));
                reservation.setEndDayOfReservation(resultSet.getDate("EndDayOfReservation"));
                reservation.setTotalPayment(resultSet.getDouble("TotalPayment"));
            }
            connection.close();
            return reservation;

    }


    public boolean checkOrderDate(long orderDate, int reservationId) throws SQLException
    {

        Connection connection = null;

        connection = dataSource.getConnection();

        int right = 0;


        String call = "{call Check_order_date(?,?)}";

        try(CallableStatement statement = connection.prepareCall(call))

        {
            statement.setDate(1, new Date(orderDate));
            statement.setInt(2, reservationId);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                right = resultSet.getInt("ok");
            }
        }
        connection.close();

        if(right==1)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }

    public void addServiceToReservation(String serviceName, long orderDate, int reservationId) throws SQLException
    {
        Connection  connection = dataSource.getConnection();

        String call = "{call Add_service_guest(?,?,?)}";

       CallableStatement statement = connection.prepareCall(call);


            statement.setString(1, serviceName);
            statement.setDate(2, new Date(orderDate));
            statement.setInt(3, reservationId);
            statement.execute();

        connection.close();
    }

}
