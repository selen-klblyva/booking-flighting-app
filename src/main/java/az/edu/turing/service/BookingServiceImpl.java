package az.edu.turing.service;

import az.edu.turing.dao.impl.BookingDao;
import az.edu.turing.dto.BookingDto;

import java.util.List;

public class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao;
    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public void saveBooking(BookingDto bookingDto) {

    }

    @Override
    public void cancelBooking(long bookingId) {

    }

    @Override
    public List<BookingDto> getAllBooking() {
        return List.of();
    }

    @Override
    public BookingDto getBookingById(long bookingId) {
        return null;
    }

    @Override
    public List<BookingDto> getAllBookingByPassengerName(String passengerNames) {
        return List.of();
    }

}
