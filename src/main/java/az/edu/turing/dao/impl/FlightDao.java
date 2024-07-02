package az.edu.turing.dao.impl;

import az.edu.turing.dao.entity.FlightEntity;

import java.sql.SQLException;
import java.util.List;

public interface FlightDao {
    void save(FlightEntity entity) throws SQLException;

    List<FlightEntity> findAll();

    FlightEntity findById(long id);

    List<FlightEntity> findByOrigin(String origin);

    void cancelFlight(long flightId);

}
