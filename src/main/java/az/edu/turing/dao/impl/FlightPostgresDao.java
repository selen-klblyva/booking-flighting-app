package az.edu.turing.dao.impl;

import az.edu.turing.dao.entity.Cities;
import az.edu.turing.dao.entity.FlightEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class FlightPostgresDao implements FlightDao {
    private static final String ADD_FLIGHT="INSERT INTO flight(origin, destination, departure_time, free_seats)\n"+
    "VALUES(?, ?,?,?);";
    private static final String findAllFlightSQL = "select *from flight;";
    private static final String findFlightById = "select *from flight where id=?";
    private static final String findFlightByOrigin = "select *from flight where origin=?";
    private static final String cancelFlightSQL= "delete from flight where id=?";


    @Override
    public void save(FlightEntity entity) throws SQLException {
           Connection con=null;
           try{
              con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                      "postgres",
                      "selback2486");
              con.setAutoCommit(false);
              PreparedStatement query=con.prepareStatement(ADD_FLIGHT);
              query.setString(1,entity.getOrigin().name());
              query.setString(2,entity.getDestination().name());
              query.setTimestamp(3, Timestamp.valueOf(entity.getDepartureTime()));
              query.setInt(4,entity.getNumberOfSeats());
              query.executeUpdate();
              con.commit();
           }catch(SQLException e){
               System.out.println();
               try {
                   con.rollback();
               } catch (SQLException ex) {
                   System.out.println("Flight Entity can not be save");
               }
        }

    }

    @Override
    public List<FlightEntity> findAll() {
        List<FlightEntity> flightEntities=new ArrayList<>();
        Connection con=null;
        try{
            con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486"
            );
            PreparedStatement query=con.prepareStatement(findAllFlightSQL);
            ResultSet resultSet=query.executeQuery();
            while(resultSet.next()){
                long id=resultSet.getLong("id");
                String origin=resultSet.getString("origin");
                String destination=resultSet.getString("destination");
                LocalDateTime departureTime=resultSet.getTimestamp("departure_time").toLocalDateTime();
                int numOfSeats=resultSet.getInt("free_seats");
                flightEntities.add(new FlightEntity(id,Cities.valueOf(origin),Cities.valueOf(destination),departureTime,numOfSeats));
            }

        }catch(SQLException e){
            System.out.println("Flight Entity can not be find");
        }
        return flightEntities;
    }

    @Override
    public FlightEntity findById(long id) {
        Connection con=null;
        try{
            con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486");
            PreparedStatement query=con.prepareStatement(findFlightById);
            query.setLong(1,id);
            ResultSet resultSet=query.executeQuery();
            while (resultSet.next()) {
                long flightId = resultSet.getLong("id");
                String origin = resultSet.getString("origin");
                String destination = resultSet.getString("destination");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                int numOfSeats = resultSet.getInt("free_seats");
                return new FlightEntity(flightId, Cities.valueOf(origin), Cities.valueOf(destination), departureTime, numOfSeats);
            }

        }catch(SQLException e){
            System.out.println("Flight did not find by id:"+e.getMessage());
        }
        return null;
    }

    @Override
    public List<FlightEntity> findByOrigin(String origin) {
        List<FlightEntity> flightEntities = new ArrayList<>();
        Connection conn;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486");

            PreparedStatement query = conn.prepareStatement(findFlightByOrigin);
            query.setString(1,origin);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long flightId = resultSet.getLong("id");
                String originCity= resultSet.getString("origin");
                String destination = resultSet.getString("destination");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                int numOfSeats = resultSet.getInt("free_seats");
                flightEntities.add(new FlightEntity(flightId, Cities.valueOf(originCity), Cities.valueOf(destination), departureTime, numOfSeats));
            }

        } catch (SQLException e) {
            System.out.println("Flight can not be found by origin:"+e.getMessage());
        }
        return flightEntities;
    }

    @Override
    public void cancelFlight(long id) {
        Connection conn = null;
        try{
            conn=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486");
            PreparedStatement query=conn.prepareStatement(cancelFlightSQL);
            conn.setAutoCommit(false);
            query.setLong(1,id);
            query.executeUpdate();
            conn.commit();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            try{
                conn.rollback();
            }catch(SQLException e1){
                System.out.println("Flight can not be cancelled:");
            }
        }

    }
}
