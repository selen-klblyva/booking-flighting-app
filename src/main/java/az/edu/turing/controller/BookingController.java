package az.edu.turing.controller;

import az.edu.turing.dto.BookingDto;

import java.util.List;

public interface BookingController {
    void saveBooking(BookingDto bookingDto);

    void cancelBooking(long bookingId);

    List<BookingDto> getAllBooking();

    BookingDto getBookingById(long bookingId);

    List<BookingDto> getAllBookingByPassengerName(String passengerNames);
}
