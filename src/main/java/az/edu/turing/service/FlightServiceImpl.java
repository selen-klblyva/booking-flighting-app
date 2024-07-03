package az.edu.turing.service;

import az.edu.turing.dao.entity.Cities;
import az.edu.turing.dao.entity.FlightEntity;
import az.edu.turing.dao.impl.FlightDao;
import az.edu.turing.dto.CriteriaDto;
import az.edu.turing.dto.FlightDto;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class FlightServiceImpl implements FlightService {
    private final FlightDao flightDao;

    public FlightServiceImpl(FlightDao flightDao) {
        this.flightDao = flightDao;
    }
    @Override
    public void saveFlight(FlightDto flightDto) throws SQLException {
           FlightEntity flightEntity = new FlightEntity(flightDto.getOrigin(),flightDto.getDestination(),
                   flightDto.getDepartureTime(),flightDto.getNumberOfSeats());
           flightDao.save(flightEntity);
    }

    @Override
    public void cancelFlight(long flightId) {
         flightDao.cancelFlight(flightId);
    }

    @Override
    public List<FlightDto> getAllFlight() {
           return flightDao.findAll().stream()
                   .map(flight ->new FlightDto(flight.getId(),
                           flight.getOrigin(),flight.getDestination(),
                           flight.getDepartureTime(),flight.getNumberOfSeats()))
                   .collect(Collectors.toList());
    }

    @Override
    public List<FlightDto> findFlightByOrigin(String origin) {
        return flightDao.findByOrigin(String origin).stream()
                .map()
    }

    @Override
    public FlightDto findFlightById(long flightId) {
        FlightEntity flightDto=flightDao.findById(flightId);
        if(flightDto==null){
            System.out.println("flight not found");
        }
        return new FlightDto(.getOrigin(),flightDto.getDestination(),
                flightDto.getDepartureTime(),flightDto.getNumberOfSeats());
    }

    @Override
    public List<FlightDto> getNext24HoursFlights(Cities origin) {
        return List.of();
    }

    @Override
    public List<FlightDto> getFlightByCriteria(CriteriaDto criteria) {
        return List.of();
    }
}
