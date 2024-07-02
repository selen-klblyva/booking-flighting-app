package az.edu.turing.service;

import az.edu.turing.dao.entity.Cities;
import az.edu.turing.dao.entity.FlightEntity;
import az.edu.turing.dao.impl.FlightDao;
import az.edu.turing.dao.impl.FlightPostgresDao;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) throws SQLException {
       FlightDao flightDao = new FlightPostgresDao();
       flightDao.save(new FlightEntity(Cities.MADRID,Cities.AMSTERDAM, LocalDateTime.now(),100));
    }
}
