package com.apap.tutorial4.repository;

import com.apap.tutorial4.model.FlightModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDB extends JpaRepository<FlightModel, Long> {
    @Query("SELECT f FROM FlightModel f WHERE f.flightNumber = ?1")
    List<FlightModel> findFlightByName(String flightNumber);

    FlightModel findByFlightNumber(String flightNumber);
}
