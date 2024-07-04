package az.edu.turing.controller;

import az.edu.turing.dao.impl.BookingDao;
import az.edu.turing.dto.BookingDto;
import az.edu.turing.service.BookingService;

import java.util.List;

public class BookingControllerImpl implements BookingController {
      private final BookingService bookingService;

      public BookingControllerImpl(BookingService bookingService) {
          this.bookingService = bookingService;
      }

    @Override
    public void saveBooking(BookingDto bookingDto) {
        bookingService.saveBooking(bookingDto);
    }

    @Override
    public void cancelBooking(long bookingId) {
           bookingService.cancelBooking(bookingId);
    }

    @Override
    public List<BookingDto> getAllBooking() {
        return bookingService.getAllBooking();
    }

    @Override
    public BookingDto getBookingById(long bookingId) {
        return getBookingById(bookingId);
    }

    @Override
    public List<BookingDto> getAllBookingByPassengerName(String passengerNames) {
        return bookingService.getAllBookingByPassengerName(passengerNames);
    }

}
