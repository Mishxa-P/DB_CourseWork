package com.coursework.controllers;

import com.coursework.dto.*;
import com.coursework.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@Controller
@RequestMapping("guest")
public class GuestController
{
    @Autowired
    GuestService guestService;

    @GetMapping("/")
    public String getMain(Model model)
    {
        try
        {
        model.addAttribute("guest", guestService.getInfo(guestService.getGuestId(SecurityContextHolder.getContext().getAuthentication().getName())));
            return "guest-main";
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
            model.addAttribute("guest", guestService.getInfo(guestService.getGuestId(SecurityContextHolder.getContext().getAuthentication().getName())));
            return "guest-info";
        } catch (Exception throwable)
        {
            return "Ошибка";
        }
    }

    @GetMapping ("/getFreeRooms")
    public String getFreeRooms(Model model)
    {
        model.addAttribute("freeroom", new FreeRoom());
        return "get-free-rooms";

    }

    @PostMapping("/getFreeRooms")
    public String getFreeRooms(Model model, @ModelAttribute("freeroom") FreeRoom freeRoom)
    {
        try
        {
            ArrayList<Room> rooms = guestService.getFreeRooms(freeRoom.getStartDate().getTime(), freeRoom.getEndDate().getTime());
            model.addAttribute("rooms", rooms);
            return "free-rooms";
        } catch (Exception throwable)
        {
            return "Ошибка";
        }
    }

    @GetMapping("/add/reservation")
    public String addReservation(Model model)
    {
        model.addAttribute("reservation", new Reservation());
        return "add-reservation";

    }

    @PostMapping("/add/reservation")
    public String addReservationForGuest(@ModelAttribute("reservation") Reservation reservation)
    {
        try {
            guestService.addReservation(reservation.getRoomId(), guestService.getGuestId(SecurityContextHolder.getContext().getAuthentication().getName()),
                    reservation.getStartDayOfReservation().getTime(), reservation.getEndDayOfReservation().getTime());
            return "redirect:/guest/getReservations";
        } catch (Exception throwable)
        {
            return "guest-exception";
        }
    }

    @GetMapping("/getReservations")
    public String getGuestReservations(Model model)
    {
        try
        {
            ArrayList<Reservation> reservations = guestService.getGuestReservation(guestService.getGuestId(SecurityContextHolder.getContext().getAuthentication().getName()));
            model.addAttribute("reservations", reservations);

            return "all-guest-reservations";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }

    }

    @GetMapping("/getAllServices")
    public String getAllServices(Model model)
    {
        try {
            ArrayList<Service> services = guestService.getAllServices();
            model.addAttribute("services", services);

            return "guest-all-services";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/add/service/{reservationId}")
    public String addService(Model model, @PathVariable int reservationId)
    {
        try
        {
            ArrayList<Service> services = guestService.getAllServices();
            RenderedService renderedService = new RenderedService();
            renderedService.setReservationId(reservationId);
            model.addAttribute("service", new Service());
            model.addAttribute("services", services);
            model.addAttribute("renderedservice", renderedService);
            return "add-service-to-reservation";
        } catch (Exception throwable)
        {

            return "guest-exception";
        }

    }

    @PostMapping("/add/service/{reservationId}")
    public String addService(@PathVariable int reservationId, @ModelAttribute("service") Service service, @ModelAttribute("renderedservice") RenderedService renderedService, Model model)
    {
        try
        {

            if (renderedService.getOrderDate()==null)
            {
                model.addAttribute("successMessage", "Пустая дата");
                ArrayList<Service> services = guestService.getAllServices();
                model.addAttribute("services", services);
                return "add-service-to-reservation";
            }

            if (!guestService.checkOrderDate(renderedService.getOrderDate().getTime(), renderedService.getReservationId()))
            {
                model.addAttribute("successMessage", "Введённая дата заказа услуги не совпадает с выбранной бронью");
                ArrayList<Service> services = guestService.getAllServices();
                model.addAttribute("services", services);
                return "add-service-to-reservation";
            }

            System.out.println(service.getServiceName());

            guestService.addServiceToReservation(service.getServiceName(), renderedService.getOrderDate().getTime(), renderedService.getReservationId());

            return "redirect:/guest/getReservations";
        } catch (Exception throwable)
        {
            return "guest-exception";
        }
    }


}
