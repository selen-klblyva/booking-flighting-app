package az.edu.turing.service;

import az.edu.turing.dao.entity.BookingEntity;
import az.edu.turing.dao.entity.Cities;
import az.edu.turing.dao.entity.FlightEntity;
import az.edu.turing.dao.impl.BookingDao;
import az.edu.turing.dao.impl.BookingPostgresDao;
import az.edu.turing.dao.impl.FlightDao;
import az.edu.turing.dao.impl.FlightPostgresDao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Application {
    public static void main(String[] args){

        //FlightDao flightDao = new FlightPostgresDao();
        BookingDao bookingDao = new BookingPostgresDao();
        //bookingDao.cancelBooking(3);
        //System.out.println(bookingDao.findAll());
        //bookingDao.cancelBooking(2);
        //System.out.println(bookingDao.findAll());
        //flightDao.save(new FlightEntity(Cities.MADRID, Cities.AMSTERDAM, LocalDateTime.now(), 100));
        // Burda error var System.out.println(flightDao.findAll());
        //System.out.println(flightDao.findById(3));
        //System.out.println(flightDao.findByOrigin("MADRID"));
        //flightDao.cancelFlight(5);
        //System.out.println(bookingDao.findById(1));
        //System.out.println(bookingDao.findByFullName("Selen Kalbaliyeva"));

    }
}
