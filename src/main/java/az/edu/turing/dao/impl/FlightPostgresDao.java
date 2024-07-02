package az.edu.turing.dao.impl;

import az.edu.turing.dao.entity.FlightEntity;

import java.sql.*;
import java.util.List;

public class FlightPostgresDao implements FlightDao {
    public static final String ADD_FLIGHT="INSERT INTO flight(origin, destination, departure_time, free_seats)\n"+
    "VALUES(?, ?,?,?);";

    @Override
    public void save(FlightEntity entity) throws SQLException {
          Connection con=null;
           try{
              con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                      "postgres",
                      "postgres");
              con.setAutoCommit(false);
              PreparedStatement query=con.prepareStatement(ADD_FLIGHT);
              query.setString(1,entity.getOrigin().name());
              query.setString(2,entity.getDestination().name());
              query.setTimestamp(3, Timestamp.valueOf(entity.getDepartureTime()));
              query.setInt(4,entity.getNumberOfSeats());
              query.executeUpdate();
              con.commit();
           }catch(SQLException e){
               System.out.println("This will give error:"+e.getMessage());
               try{
                   con.rollback();
               }catch(SQLException e1){
                   System.out.println("This flight can not be saved:"+e1.getMessage());
               }
        }
    }

    @Override
    public List<FlightEntity> findAll() {
        return List.of();
    }

    @Override
    public FlightEntity findById(long id) {
        return null;
    }

    @Override
    public List<FlightEntity> findByOrigin(String origin) {
        return List.of();
    }

    @Override
    public void cancelFlight(long flightId) {

    }
}
