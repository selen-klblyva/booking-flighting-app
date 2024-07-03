package az.edu.turing.service;

import az.edu.turing.dto.BookingDto;

import java.util.List;

public interface BookingService {
    void saveBooking(BookingDto bookingDto);

    void cancelBooking(long bookingId);

    List<BookingDto> getAllBooking();

    BookingDto getBookingById(long bookingId);

    List<BookingDto> getAllBookingByPassengerName(String passengerNames);
}
