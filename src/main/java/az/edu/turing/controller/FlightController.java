package az.edu.turing.controller;

import az.edu.turing.dao.entity.Cities;
import az.edu.turing.dto.CriteriaDto;
import az.edu.turing.dto.FlightDto;

import java.sql.SQLException;
import java.util.List;

public interface FlightController {
    void saveFlight(FlightDto flightDto) throws SQLException;

    void cancelFlight(long flightId);

    List<FlightDto> getAllFlight();

    FlightDto findFlightById(long fligtId);

    List<FlightDto> findFlightByOrigin(String origin);

    List<FlightDto> getNext24HoursFlights(Cities origin);

    List<FlightDto> getFlightByCriteria(CriteriaDto criteria);

}
