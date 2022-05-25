package com.coursework.services;

import com.coursework.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;


@Component
    public class ManagerService
    {
        @Autowired
        DataSource dataSource;

        public Manager getInfo(int managerId) throws SQLException
        {
            Manager manager = new Manager();
            Connection connection = null;
            Statement statement = null;

            connection = dataSource.getConnection();

            statement = connection.createStatement();

            String SQL = "Select * FROM Managers Where ManagerId = " + managerId;

            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next())
            {
                manager.setUserId(resultSet.getInt("UserId"));
                manager.setSurname(resultSet.getString("Surname"));
                manager.setName(resultSet.getString("Name"));
                manager.setPatronymic(resultSet.getString("Patronymic"));
                manager.setPhoneNumber(resultSet.getString("PhoneNumber"));

            }
            resultSet.close();
            statement.close();
            connection.close();
            return manager;
        }

        public int getManagerId(String username) throws SQLException
        {
            int managerId = 0;

            Connection connection = dataSource.getConnection();

            String call = "{call Get_manager_id(?)}";

            try(CallableStatement statement = connection.prepareCall(call))
            {
                statement.setString(1, username);

                ResultSet resultSet =  statement.executeQuery();
                while(resultSet.next())
                {
                    managerId = resultSet.getInt("ManagerId");
                }
                connection.close();
                return managerId;
            }
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

        public ArrayList<DressedReservation> getAllReservations() throws SQLException
        {
            ArrayList<DressedReservation> reservations = new ArrayList<>();
            Connection connection = null;
            Statement statement = null;

            connection = dataSource.getConnection();

            statement = connection.createStatement();

            String SQL = "SELECT * FROM Fulled_dressed_reservations";

            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next())
            {
                DressedReservation reservation = new DressedReservation();
                reservation.setReservationId(resultSet.getInt("ReservationNumber"));
                reservation.setRoomId(resultSet.getInt("Room"));
                reservation.setName(resultSet.getString("Name"));
                reservation.setSurname(resultSet.getString("Surname"));
                reservation.setPhoneNumber(resultSet.getString("PhoneNumber"));
                reservation.setStartDayOfReservation(resultSet.getDate("StartDayOfReservation"));
                reservation.setEndDayOfReservation(resultSet.getDate("EndDayOfReservation"));
                reservation.setTotalPayment(resultSet.getDouble("TotalPayment"));
                reservations.add(reservation);
            }

            resultSet.close();
            statement.close();
            connection.close();
            return reservations;
        }

        public void changeStartDayOfReservation(int reservationId, long startDate) throws SQLException
        {
            Connection connection = null;

            connection = dataSource.getConnection();

            String call = "{call Change_start_day_of_reservation(?,?)}";



            try(CallableStatement statement = connection.prepareCall(call))
            {
                statement.setInt(1, reservationId);
                statement.setDate(2, new Date(startDate));
                statement.execute();
            }
            connection.close();
        }

        public void changeEndDayOfReservation(int reservationId, long endDate) throws SQLException
        {
            Connection connection = null;

            connection = dataSource.getConnection();

            String call = "{call Change_end_day_of_reservation(?,?)}";


            try(CallableStatement statement = connection.prepareCall(call))
            {
                statement.setInt(1, reservationId);
                statement.setDate(2, new Date(endDate));
                statement.execute();
            }
            connection.close();
        }

        public ArrayList<Guest> getAllGuestsWithRenderedService() throws SQLException
        {
            ArrayList<Guest> guests = new ArrayList<>();
            Connection connection = null;
            Statement statement = null;

            connection = dataSource.getConnection();


            statement = connection.createStatement();

            String SQL = "Select DISTINCT Guests.GuestId, Surname, Name, Patronymic, PhoneNumber, Passport, DateOfBirth FROM Guests\n" +
                    "INNER JOIN Reservations ON Guests.GuestId=Reservations.GuestId \n" +
                    "INNER JOIN RenderedServices ON Reservations.ReservationId = RenderedServices.ReservationId";

            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next())
            {
                Guest guest = new Guest();
                guest.setGuestId(resultSet.getInt("GuestId"));
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

        public ArrayList<RenderedService> getReservationRenderedServices(int reservationId) throws SQLException
        {
            ArrayList<RenderedService> services = new ArrayList<>();
            Connection connection = null;

            connection = dataSource.getConnection();

            String call = "{call Show_rendered_services(?)}";


            try(CallableStatement statement = connection.prepareCall(call))
            {
                statement.setInt(1, reservationId);

                ResultSet resultSet =  statement.executeQuery();
                while(resultSet.next())
                {
                    RenderedService service = new RenderedService();
                    service.setReservationId(reservationId);
                    service.setServiceName(resultSet.getString("ServiceName"));
                    service.setServiceCost(resultSet.getDouble("ServiceCost"));
                    service.setOrderDate(resultSet.getDate("OrderDate"));
                    services.add(service);
                }
                connection.close();
                return services;
            }
        }

        public void deleteServiceFromReservation(String serviceName, long orderDate, int reservationId) throws SQLException
        {
            Connection connection = null;

            connection = dataSource.getConnection();

            String call = "{call Delete_service_guest(?,?,?)}";


            try(CallableStatement statement = connection.prepareCall(call))
            {
                statement.setString(1, serviceName);
                statement.setDate(2, new Date(orderDate));
                statement.setInt(3,  reservationId);

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

    }
