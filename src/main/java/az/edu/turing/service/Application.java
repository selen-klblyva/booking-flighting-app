package az.edu.turing.service;

import az.edu.turing.dao.entity.Cities;
import az.edu.turing.dao.entity.FlightEntity;
import az.edu.turing.dao.impl.FlightDao;
import az.edu.turing.dao.impl.FlightPostgresDao;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args){

        FlightDao flightDao = new FlightPostgresDao();
        //flightDao.save(new FlightEntity(Cities.MADRID, Cities.AMSTERDAM, LocalDateTime.now(), 100));
        // Burda error var System.out.println(flightDao.findAll());
        //System.out.println(flightDao.findById(3));
        //System.out.println(flightDao.findByOrigin("MADRID"));
        //flightDao.cancelFlight(5);

    }
}
