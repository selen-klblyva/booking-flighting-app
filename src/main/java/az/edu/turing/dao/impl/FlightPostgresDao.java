package az.edu.turing.dao.impl;

import az.edu.turing.dao.entity.FlightEntity;

import java.util.List;

public class FlightPostgresDao implements FlightDao {
    @Override
    public void save(FlightEntity entity) {

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
