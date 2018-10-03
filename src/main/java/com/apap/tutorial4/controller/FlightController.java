package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.IFlightService;
import com.apap.tutorial4.service.IPilotService;
import com.apap.tutorial4.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class FlightController {

    private final static Logger LOGGER = Logger.getLogger(FlightController.class.getName());

    @Autowired
    private IFlightService flightService;

    @Autowired
    private IPilotService pilotService;

    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        LOGGER.log(Level.INFO, String.format("Pilot License Number: %s", pilot.getLicenseNumber()));

        FlightModel flight = new FlightModel();
        flight.setPilot(pilot);

        model.addAttribute("flight", flight);
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add", method = RequestMethod.POST)
    private String addSubmitFlight(@ModelAttribute FlightModel newFlight) {
        flightService.addFlight(newFlight);
        return "add";
    }

    @RequestMapping(value = "/flight/view/{id}", method = RequestMethod.GET)
    private String viewFlight(@PathVariable(value = "id") Long id, Model model) {
        FlightModel flight = flightService.getFlightById(id);
        model.addAttribute("flight", flight);
        return "view-flight";
    }

    @RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.GET)
    private String deleteFlight(@PathVariable(value = "id") Long id) {
        flightService.deleteFlightById(id);
        return "delete";
    }

    @RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
    private String updateFlight(@PathVariable(value = "id") Long id, Model model) {
        FlightModel flight = flightService.getFlightById(id);
        model.addAttribute("flight", flight);
        model.addAttribute("pilots", ((PilotService) pilotService).getManager().findAll());
        return "updateFlight";
    }

    @RequestMapping(value = "/flight/update/{id}", method = RequestMethod.POST)
    private String updateSubmitFlight(@PathVariable(value = "id") Long id, @ModelAttribute FlightModel flight) {
        flight.setId(id);
        LOGGER.log(Level.INFO, "Flight Id: " + flight.toString());
        flightService.updateFlight(flight);
        return "redirect:/flight/view/" + id;
    }
}
