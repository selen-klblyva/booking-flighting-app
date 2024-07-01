package az.edu.turing.dao.entity;

import java.util.List;
import java.util.Objects;

public class BookingEntity {
    public static long MAX_ID = 0;
    private long id;
    private long flightId;
    private List<String> passengerNames;

    public BookingEntity() {

    }

    public BookingEntity(long id, long flightId, List<String> passengerNames) {
        this.id = id;
        this.flightId = flightId;
        this.passengerNames = passengerNames;
    }

    public BookingEntity(long flightId, List<String> passengerNames) {
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
        return id == that.id && flightId == that.flightId && Objects.equals(passengerNames, that.passengerNames);
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
