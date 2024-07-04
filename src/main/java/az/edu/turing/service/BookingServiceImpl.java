package az.edu.turing.service;

import az.edu.turing.dao.entity.BookingEntity;
import az.edu.turing.dao.impl.BookingDao;
import az.edu.turing.dto.BookingDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao;
    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public void saveBooking(BookingDto bookingDto) {
          BookingEntity bookingEntity = new BookingEntity(bookingDto.getId(),
                  bookingDto.getFlightId(),bookingDto.getPassengerNames());
          bookingDao.save(bookingEntity);
    }

    @Override
    public void cancelBooking(long bookingId) {
           bookingDao.cancelBooking(bookingId);
    }

    @Override
    public List<BookingDto> getAllBooking() {
        return bookingDao.findAll().stream()
                .map(booking -> new BookingDto(booking.getId(),booking.getFlightId(),
                        booking.getPassengerName()))
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto getBookingById(long bookingId) {
              BookingEntity bookingEntity = bookingDao.findById(bookingId);
              if (bookingEntity == null) {
                  return null;
              }
              return new BookingDto(bookingEntity.getId(),bookingEntity.getFlightId(),
                      bookingEntity.getPassengerName());
    }

    @Override
    public List<BookingDto> getAllBookingByPassengerName(String passengerNames) {
            return bookingDao.findByFullName(passengerNames).stream()
                    .map(booking-> new BookingDto(booking.getId(),
                            booking.getFlightId(),booking.getPassengerName()))
                    .collect(Collectors.toList());
    }

}
