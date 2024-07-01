package az.edu.turing.dto;

import az.edu.turing.dao.entity.BookingEntity;

import java.util.List;
import java.util.Objects;

public class BookingDto {
    public static long MAX_ID = 0;
    private long id;
    private long flightId;
    private List<String> passengerNames;

    public BookingDto() {

    }

    public BookingDto(long id, long flightId, List<String> passengerNames) {
        this.id = id;
        this.flightId = flightId;
        this.passengerNames = passengerNames;
    }

    public BookingDto(long flightId, List<String> passengerNames) {
        this.flightId = flightId;
        this.passengerNames = passengerNames;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getPassengerNames() {
        return passengerNames;
    }

    public void setPassengerNames(List<String> passengerNames) {
        this.passengerNames = passengerNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return id == that.getId() && flightId == that.getFlightId() && Objects.equals(passengerNames, that.getPassengerNames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightId, passengerNames);
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", passengerNames=" + passengerNames +
                '}';
    }
}
