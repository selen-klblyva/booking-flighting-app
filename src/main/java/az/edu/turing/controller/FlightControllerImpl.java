package az.edu.turing.controller;

import az.edu.turing.dao.entity.Cities;
import az.edu.turing.dto.CriteriaDto;
import az.edu.turing.dto.FlightDto;
import az.edu.turing.service.FlightService;

import java.sql.SQLException;
import java.util.List;

public class FlightControllerImpl implements FlightController {
    private final FlightService flightService;

    public FlightControllerImpl(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public void saveFlight(FlightDto flightDto) throws SQLException {
        flightService.saveFlight(flightDto);
    }

    @Override
    public void cancelFlight(long flightId) {
        flightService.cancelFlight(flightId);
    }

    @Override
    public List<FlightDto> getAllFlight() {
        return flightService.getAllFlight();
    }

    @Override
    public FlightDto findFlightById(long fligtId) {
        return flightService.findFlightById(fligtId);
    }

    @Override
    public List<FlightDto> findFlightByOrigin(String origin) {
        return flightService.findFlightByOrigin(origin);
    }

    @Override
    public List<FlightDto> getNext24HoursFlights(Cities origin) {
        return flightService.getNext24HoursFlights(origin);
    }

    @Override
    public List<FlightDto> getFlightByCriteria(CriteriaDto criteria) {
        return flightService.getFlightByCriteria(criteria);
    }
}
