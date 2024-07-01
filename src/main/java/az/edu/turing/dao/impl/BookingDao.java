package az.edu.turing.dao.impl;

import az.edu.turing.dao.entity.BookingEntity;

import java.util.List;

public interface BookingDao {
    void save(BookingEntity entity);

    void cancelBooking(long bookingId);

    List<BookingEntity> findAll();

    BookingEntity findById(long id);

    List<BookingEntity> findByFullName(String passengerNames);
}
