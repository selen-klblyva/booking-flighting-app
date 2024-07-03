package az.edu.turing.service;

import az.edu.turing.dao.entity.Cities;
import az.edu.turing.dto.CriteriaDto;
import az.edu.turing.dto.FlightDto;

import java.sql.SQLException;
import java.util.List;

public interface FlightService {
    void saveFlight(FlightDto flightDto) throws SQLException;

    void cancelFlight(long flightId);

    List<FlightDto> getAllFlight();

    List<FlightDto> findFlightByOrigin(String origin);

    FlightDto findFlightById(long flightId);

    List<FlightDto> getNext24HoursFlights(Cities origin);

    List<FlightDto> getFlightByCriteria(CriteriaDto criteria);

}
