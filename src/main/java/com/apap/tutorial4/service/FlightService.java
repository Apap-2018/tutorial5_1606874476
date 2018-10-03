package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlightService implements IFlightService {

    @Autowired
    private FlightDB flightDb;

    @Override
    public FlightModel getFlightById(Long id) {
        return flightDb.getOne(id);
    }

    @Override
    public Boolean addFlight(FlightModel flight) {
        flightDb.save(flight);
        return true;
    }

    @Override
    public Boolean deleteFlightById(long id) {
        flightDb.deleteById(id);
        return true;
    }

    @Override
    public FlightModel updateFlight(FlightModel flightModel) {
        FlightModel flightToUpdate = flightDb.getOne(flightModel.getId());
        flightToUpdate.setDestination(flightModel.getDestination());
        flightToUpdate.setFlightNumber(flightModel.getFlightNumber());
        flightToUpdate.setOrigin(flightModel.getOrigin());
        flightToUpdate.setTime(flightModel.getTime());
//        flightToUpdate.setPilot(flightModel.getPilot());

        flightDb.save(flightToUpdate);
        return flightToUpdate;
    }
}
