package az.edu.turing.service;

import az.edu.turing.dao.entity.Cities;
import az.edu.turing.dao.entity.FlightEntity;
import az.edu.turing.dao.impl.FlightDao;
import az.edu.turing.dto.CriteriaDto;
import az.edu.turing.dto.FlightDto;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        return flightDao.findByOrigin(origin).stream()
                 .map(flight -> new FlightDto(flight.getId(),
                         flight.getOrigin(),flight.getDestination(),
                         flight.getDepartureTime(),flight.getNumberOfSeats()))
                 .collect(Collectors.toList());
    }

    @Override
    public FlightDto findFlightById(long flightId) {
            FlightEntity flightEntity=flightDao.findById(flightId);
            if(flightEntity==null){
                System.out.println("Flight will not find by id");
            }
            return new FlightDto(flightEntity.getId(),flightEntity.getOrigin(),
                    flightEntity.getDestination(),flightEntity.getDepartureTime(),flightEntity.getNumberOfSeats());
    }

    @Override
    public List<FlightDto> getNext24HoursFlights(Cities origin) {
           List<FlightEntity> entities=flightDao.findAll();
           try{
               return entities.stream().filter(entity ->
                       entity.getOrigin().equals(origin) &&
                       entity.getDepartureTime().isAfter(LocalDateTime.now()) &&
                       entity.getDepartureTime().isBefore(LocalDateTime.now().plusHours(24)))
                       .map(flight -> new FlightDto(flight.getId(),flight.
                               getOrigin(),flight.getDestination(),flight.getDepartureTime(),flight.getNumberOfSeats()))
                       .collect(Collectors.toList());

           }catch(Exception e){
               System.out.println("Error while fetching next 24 hours flights: "+e.getMessage());
               return new ArrayList<>();
           }
    }

    @Override
    public List<FlightDto> getFlightByCriteria(CriteriaDto criteria) {
        List<FlightEntity> entities=flightDao.findAll();
        try{
            return entities.stream().filter(entity ->
                    entity.getDestination().equals(criteria.getDestination()) &&
                    entity.getDepartureTime().equals(criteria.getTime()) &&
                            entity.getNumberOfSeats()>=criteria.getSeats())
                    .map(flight-> new FlightDto(flight.getId(),flight.getOrigin(),
                            flight.getDestination(),flight.getDepartureTime(),flight.getNumberOfSeats()))
                    .collect(Collectors.toList());
        }catch(Exception e){
            System.out.println("It will give us error:"+e.getMessage());
            return new ArrayList<>();
        }

    }
}
