package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.IPilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PilotController {

    private final static Logger LOGGER = Logger.getLogger(PilotController.class.getName());

    @Autowired
    private IPilotService pilotService;

    @RequestMapping(value = "/")
    private String home() {
        return "home";
    }

    @RequestMapping(value = "pilot/add", method = RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("pilot", new PilotModel());
        return "addPilot";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
    private String addSubmitPilot(@ModelAttribute PilotModel newPilot) {
        pilotService.addPilot(newPilot);
        return "add";
    }

    @RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
    private String viewPilot(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        LOGGER.log(Level.INFO, pilot.toString());
        LOGGER.log(Level.INFO, pilot.getPilotFlights().toString());
        model.addAttribute("pilot", pilot);
        return "view-pilot";
    }

    @RequestMapping(value = "/pilot/update/{id}", method = RequestMethod.GET)
    private String updatePilot(@PathVariable(value = "id") Long id, Model model) {
        PilotModel pilot = pilotService.getPilotById(id);
        LOGGER.log(Level.INFO, pilot.toString());
        model.addAttribute("pilot", pilot);
        return "updatePilot";
    }

    @RequestMapping(value = "/pilot/update/{id}", method = RequestMethod.POST)
    private String updateSubmitPilot(@PathVariable(value = "id") Long id, @ModelAttribute PilotModel pilotModel) {
        pilotModel.setId(id);
        LOGGER.log(Level.INFO, pilotModel.toString());

        PilotModel pilot = pilotService.updatePilot(pilotModel);
        return "redirect:/pilot/view?licenseNumber=" + pilot.getLicenseNumber();
    }

    @RequestMapping(value = "/pilot/delete/{id}", method = RequestMethod.GET)
    private String deletePilot(@PathVariable(value = "id") Long id) {
        pilotService.deletePilotById(id);
        return "delete";
    }

}
