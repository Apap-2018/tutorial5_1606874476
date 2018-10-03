package com.apap.tutorial4.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "flight_model")
public class FlightModel extends AbstractEntity {

    @NotNull
    @Size(max = 50)
    @Column(name="flight_number", nullable = false)
    private String flightNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "origin", nullable = false)
    private String origin;

    @NotNull
    @Size(max = 50)
    @Column(name = "destination", nullable = false)
    private String destination;

    @NotNull
    @Column(name= "time")
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilot_licenseNumber", referencedColumnName = "license_number", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private PilotModel pilot;

    public FlightModel() {
    }

    public FlightModel(@NotNull @Size(max = 50) String flightNumber, @NotNull @Size(max = 50) String origin, @NotNull @Size(max = 50) String destination, @NotNull Date time, PilotModel pilot) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.pilot = pilot;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public PilotModel getPilot() {
        return pilot;
    }

    public void setPilot(PilotModel pilot) {
        this.pilot = pilot;
    }
}
