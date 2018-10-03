package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;

public interface IPilotService {

    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);

    PilotModel getPilotById(Long id);

    Boolean addPilot(PilotModel pilot);

    Boolean deletePilotById(long id);

    PilotModel updatePilot(PilotModel pilot);

}
