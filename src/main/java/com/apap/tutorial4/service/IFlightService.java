package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;

public interface IFlightService {

    Boolean addFlight(FlightModel flight);

    FlightModel getFlightById(Long id);

    Boolean deleteFlightById(long id);

    FlightModel updateFlight(FlightModel flightModel);
}
