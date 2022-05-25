package com.coursework.controllers;

import com.coursework.dto.*;
import com.coursework.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;


@Controller
@RequestMapping("manager")
public class ManagerController
{

    @Autowired
    private ManagerService managerService;

    @GetMapping("/")
    public String getMain(Model model)
    {
        try
        {
            model.addAttribute("manager", managerService.getInfo(managerService.getManagerId(SecurityContextHolder.getContext().getAuthentication().getName())));
            return "manager-main";
        } catch (Exception throwable)
        {
            return "Ошибка";
        }
    }

    @GetMapping("/getInfo")
    public String getInfo(Model model)
    {
        try
        {
            model.addAttribute("manager", managerService.getInfo(managerService.getManagerId(SecurityContextHolder.getContext().getAuthentication().getName())));
            return "manager-info";
        } catch (Exception throwable)
        {
            return "Ошибка";
        }
    }

    @GetMapping("/getAllGuests")
    public String getAllAdmins(Model model)
    {
        try {
            ArrayList<Guest> guests = managerService.getAllGuests();
            model.addAttribute("guests", guests);

            return "all-guests-manager";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/getAllReservations")
    public String getAllReservations(Model model)
    {
        try {
            ArrayList<DressedReservation> reservations = managerService.getAllReservations();
            model.addAttribute("reservations", reservations);

            return "all-reservations";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }


    @GetMapping("/change/reservation/startdate")
    public String changeStartDayOfReservation(Model model)
    {
        model.addAttribute("reservation", new Reservation());
        return "change-reservation-startdate";

    }

    @PostMapping("/change/reservation/startdate")
    public String changeStartDayOfReservation(@ModelAttribute("reservation") Reservation reservation)
    {
        try {
            managerService.changeStartDayOfReservation(reservation.getReservationId(), reservation.getStartDayOfReservation().getTime());
            return "redirect:/manager/getAllReservations";
        } catch (Exception throwable) {
            return "Ошибка";
        }
    }

    @GetMapping("/change/reservation/enddate")
    public String changeEndDayOfReservation(Model model)
    {
        model.addAttribute("reservation", new Reservation());
        return "change-reservation-enddate";

    }

    @PostMapping("/change/reservation/enddate")
    public String changeEndDayOfReservation(@ModelAttribute("reservation") Reservation reservation)
    {
        try {
            managerService.changeEndDayOfReservation(reservation.getReservationId(), reservation.getEndDayOfReservation().getTime());
            return "redirect:/manager/getAllReservations";
        } catch (Exception throwable) {
            return "Ошибка";
        }
    }

    @GetMapping("/getAllGuestsWithRenderedServices")
    public String getAllGuestsWithRenderedService(Model model)
    {
        try {
            ArrayList<Guest> guests = managerService.getAllGuestsWithRenderedService();
            model.addAttribute("guests", guests);

            return "all-guests-manager";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/getReservationRenderedServices")
    public String getReservationRenderedServices(Model model)
    {
            model.addAttribute("renderedservice", new RenderedService());

            return "get-reservation-rendered-services";
    }

    @PostMapping("/getReservationRenderedServices")
    public String getReservationRenderedServices(Model model, @ModelAttribute("renderedservice") RenderedService renderedService)
    {
        try {
            ArrayList<RenderedService> renderedServices = managerService.getReservationRenderedServices(renderedService.getReservationId());

            model.addAttribute("renderedservices", renderedServices);
            return "reservation-rendered-services";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/delete/serviceFromReservation")
    public String deleteServiceFromReservation(Model model)
    {

        try
        {
            ArrayList<Service> services = managerService.getAllServices();
            RenderedService renderedService = new RenderedService();
            model.addAttribute("service", new Service());
            model.addAttribute("services", services);
            model.addAttribute("renderedservice", renderedService);
            return "delete-service-from-reservation";
        } catch (Exception throwable)
        {
            return "guest-exception";
        }

    }

    @PostMapping("/delete/serviceFromReservation")
    public String deleteServiceFromReservation(@ModelAttribute("service") Service service, @ModelAttribute("renderedservice") RenderedService renderedService, Model model)
    {
        try {
            managerService.deleteServiceFromReservation(service.getServiceName(), renderedService.getOrderDate().getTime(), renderedService.getReservationId());
            return "redirect:/manager/getAllReservations";
        } catch (Exception throwable) {
            return "Ошибка";
        }
    }


}

