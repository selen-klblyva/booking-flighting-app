package az.edu.turing.dao.impl;

import az.edu.turing.dao.entity.BookingEntity;
import az.edu.turing.database.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingPostgresDao implements BookingDao{
    private static final String getBookingById ="SELECT booking.id, booking.flight_id, string_agg(full_name, ',') as full_names FROM booking JOIN booking_passenger bp ON booking.id = bp.booking_id JOIN passenger p ON p.id = bp.passenger_id WHERE booking.id=? GROUP BY booking.id;";
    private static final String getBookingByFullName="SELECT booking.id, booking.flight_id, string_agg(full_name, ',') as full_names FROM booking JOIN booking_passenger bp ON booking.id = bp.booking_id JOIN passenger p ON p.id = bp.passenger_id WHERE booking.id IN (SELECT booking.id FROM booking JOIN booking_passenger b ON booking.id = b.booking_id JOIN passenger p2 ON p2.id = b.passenger_id WHERE p2.full_name = ?) GROUP BY booking.id;";
    private static final String getAllBooking="SELECT booking.id, booking.flight_id, string_agg(full_name, ',') as full_names FROM booking JOIN booking_passenger bp ON booking.id = bp.booking_id JOIN passenger p ON p.id = bp.passenger_id GROUP BY booking.id;";
    private static final String createBookingSql = "INSERT INTO booking (flight_id) VALUES (?);";
    private static final String createPassengerNameSql = "INSERT INTO passenger (full_name) VALUES (?);";
    private static final String createBookingByPassengerIdSql = "INSERT INTO booking_passenger (booking_id, passenger_id) VALUES (?, ?);";

    @Override
    public void save(BookingEntity entity) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "selback2486");
             PreparedStatement query = conn.prepareStatement(createBookingSql, Statement.RETURN_GENERATED_KEYS)) {
            query.setLong(1, entity.getFlightId());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                long bookingId = generatedKeys.getLong(1);
                for (String passengerName : entity.getPassengerName()) {
                    PreparedStatement queryPassenger = conn.prepareStatement(createPassengerNameSql, Statement.RETURN_GENERATED_KEYS);
                    queryPassenger.setString(1, passengerName);
                    queryPassenger.executeUpdate();
                    ResultSet passengerKeys = queryPassenger.getGeneratedKeys();
                    if (passengerKeys.next()) {
                        long passengerId = passengerKeys.getLong(1);
                        PreparedStatement queryBookingPassenger = conn.prepareStatement(createBookingByPassengerIdSql);
                        queryBookingPassenger.setLong(1, bookingId);
                        queryBookingPassenger.setLong(2, passengerId);
                        queryBookingPassenger.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    @Override
    public void cancelBooking(long bookingId){
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "selback2486")) {
            conn.setAutoCommit(false);

            try (PreparedStatement deleteBookingPassenger = conn.prepareStatement("DELETE FROM booking_passenger WHERE booking_id = ?");
                 PreparedStatement deleteBooking = conn.prepareStatement("DELETE FROM booking WHERE id = ?")) {


                deleteBookingPassenger.setLong(1, bookingId);
                deleteBookingPassenger.executeUpdate();


                deleteBooking.setLong(1, bookingId);
                deleteBooking.executeUpdate();

                conn.commit();
            } catch (SQLException e) {

                conn.rollback();
                System.out.println("SQLException: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public BookingEntity findById(long id) {
        Connection conn=null;
        try{
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486");
            PreparedStatement query=conn.prepareStatement(getBookingById);
            query.setLong(1, id);
            ResultSet resultSet=query.executeQuery();
            while(resultSet.next()){
                long bookingId=resultSet.getLong(1);
                long flightId=resultSet.getLong(2);
                String fullNames = resultSet.getString(3);
                return new BookingEntity(bookingId, flightId, List.of(fullNames.split(",")));
            }

        }catch(SQLException e){
            System.out.println("Booking not found:"+e.getMessage());
        }
        return null;
    }



    @Override
    public List<BookingEntity> findAll() {
        List<BookingEntity> bookingEntities=new ArrayList<>();
        Connection conn=null;
        try{
            conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486" );
            PreparedStatement query=conn.prepareStatement(getAllBooking);
            ResultSet resultSet=query.executeQuery();
            while(resultSet.next()){
                long bookingId=resultSet.getLong(1);
                long flightId=resultSet.getLong(2);
                String fullNames = resultSet.getString(3);
                bookingEntities.add(new BookingEntity(bookingId, flightId, List.of(fullNames.split(","))));
            }
        }catch(SQLException e){
            System.out.println("Booking not found:"+e.getMessage());
        }
        return bookingEntities;
    }

    @Override
    public List<BookingEntity> findByFullName(String passengerNames) {
        List<BookingEntity> bookingEntities=new ArrayList<>();
        Connection conn=null;
        try{
            conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486" );
            PreparedStatement query=conn.prepareStatement(getBookingByFullName);
            query.setString(1, passengerNames);
            ResultSet resultSet=query.executeQuery();
            while(resultSet.next()){
                long bookingId=resultSet.getLong(1);
                long flightId=resultSet.getLong(2);
                String fullNames = resultSet.getString(3);
                bookingEntities.add(new BookingEntity(bookingId, flightId, List.of(fullNames.split(","))));
            }
        }catch(SQLException e){
            System.out.println("Booking not found:"+e.getMessage());
        }
        return bookingEntities;

    }
}
