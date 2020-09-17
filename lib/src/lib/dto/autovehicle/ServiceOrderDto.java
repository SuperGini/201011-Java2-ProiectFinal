package lib.dto.autovehicle;

import java.time.Instant;

public class ServiceOrderDto {

    private int id;

    private double total;

    private Instant timeStamp;

    public ServiceOrderDto(int id, double total, Instant timeStamp) {
        this.id = id;
        this.total = total;
        this.timeStamp = timeStamp;
    }

    public ServiceOrderDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
}
