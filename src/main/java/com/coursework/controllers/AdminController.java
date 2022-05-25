package com.coursework.controllers;

import com.coursework.dto.*;
import com.coursework.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.ArrayList;


@Controller
@RequestMapping("admin")
    public class AdminController
{

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String getMain(Model model)
    {
        try
        {
            model.addAttribute("admin", adminService.getInfo(adminService.getAdminId(SecurityContextHolder.getContext().getAuthentication().getName())));
            return "admin-main";
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
            model.addAttribute("admin", adminService.getInfo(adminService.getAdminId(SecurityContextHolder.getContext().getAuthentication().getName())));
            return "admin-info";
        } catch (Exception throwable)
        {
            return "Ошибка";
        }
    }

    @GetMapping("/getAllAdmins")
    public String getAllAdmins(Model model) {
        try {
            ArrayList<Admin> admins = adminService.getAllAdmins();
            model.addAttribute("admins", admins);

            return "all-admins";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/getAllManagers")
    public String getAllManagers(Model model) {
        try {
            ArrayList<Manager> managers = adminService.getAllManagers();
            model.addAttribute("managers", managers);

            return "all-managers";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/getAllRooms")
    public String getAllRooms(Model model) {
        try {
            ArrayList<Room> rooms = adminService.getAllRooms();
            model.addAttribute("rooms", rooms);

            return "all-rooms";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/getAllGuests")
    public String getAllGuests(Model model) {
        try {
            ArrayList<Guest> guests = adminService.getAllGuests();
            model.addAttribute("guests", guests);

            return "all-guests";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/getAllServices")
    public String getAllServices(Model model) {
        try {
            ArrayList<Service> services = adminService.getAllServices();
            model.addAttribute("services", services);

            return "all-services";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/add/room")
    public String newRoom(Model model) {
        model.addAttribute("room", new Room());

        return "add-room";

    }


    @PostMapping("/add/room")
    public String addRoom(@ModelAttribute("room") Room room)
    {
        try
        {
            adminService.addRoom(room.getRoomNumber(), room.getCostOfLiving());
            return "redirect:/admin/getAllRooms";
        } catch (Exception throwable)
        {
            return "Ошибка";
        }
    }

    @GetMapping("/delete/room")
    public String deleteRoom(Model model)
    {
        model.addAttribute("room", new Room());
        return "delete-room";

    }


    @PostMapping("/delete/room")
    public String deleteRoom(@ModelAttribute("room") Room room)
    {
        try
        {
            adminService.deleteRoom(room.getRoomId());
            return "redirect:/admin/getAllRooms";
        } catch (Exception throwable)
        {
            return "delete-room-exception";
        }
    }

    @GetMapping("/delete/manager")
    public String deleteManager(Model model)
    {
        model.addAttribute("manager", new Manager());
        return "delete-manager";

    }

    @PostMapping("/delete/manager")
    public String deleteManager(@ModelAttribute("manager") Manager manager)
    {
        try
        {
            adminService.deleteManager(manager.getManagerId());
            return "redirect:/admin/getAllManagers";
        } catch (Exception throwable)
        {
            return "Ошибка";
        }
    }

    @GetMapping("/add/service")
    public String newService(Model model)
    {
        model.addAttribute("service", new Service());
        return "add-service";

    }

    @PostMapping("/add/service")
    public String addService(@ModelAttribute("service") Service service)
    {
        try {
            adminService.addService(service.getServiceName(), service.getServiceCost(), service.getDescription());
            return "redirect:/admin/getAllServices";
        } catch (Exception throwable) {
            return "Ошибка";
        }
    }

    @GetMapping("/delete/service")
    public String deleteService(Model model)
    {
        model.addAttribute("service", new Service());
        return "delete-service";

    }

    @PostMapping("/delete/service")
    public String deleteService(@ModelAttribute("service") Service service)
    {
        try
        {
            adminService.deleteService(service.getServiceId());
            return "redirect:/admin/getAllServices";
        } catch (Exception throwable)
        {
            return "delete-service-exception";
        }
    }

    @GetMapping("/change/service/cost")
    public String changeServiceCost(Model model)
    {
        model.addAttribute("service", new Service());
        return "change-service-cost";

    }

    @PostMapping("/change/service/cost")
    public String changeServiceCost(@ModelAttribute("service") Service service)
    {
        try {
            adminService.changeServiceCost(service.getServiceId(), service.getServiceCost());
            return "redirect:/admin/getAllServices";
        } catch (Exception throwable) {
            return "Ошибка";
        }
    }

    @GetMapping("/change/service/description")
    public String changeServiceDescription(Model model)
    {
        model.addAttribute("service", new Service());
        return "change-service-description";

    }

    @PostMapping("/change/service/description")
    public String changeServiceDescription(@ModelAttribute("service") Service service)
    {
        try {
            adminService.changeServiceDescription(service.getServiceId(), service.getDescription());
            return "redirect:/admin/getAllServices";
        } catch (Exception throwable) {
            return "Ошибка";
        }
    }

    @GetMapping("/change/room/")
    public String changeRoom(Model model)
    {
        model.addAttribute("room", new Room());
        return "change-room";

    }

    @PostMapping("/change/room/")
    public String changeServiceDescription(@ModelAttribute("room") Room room)
    {
        try {
            adminService.changeRoom(room.getRoomId(),room.getRoomNumber(), room.getCostOfLiving());
            return "redirect:/admin/getAllRooms";
        } catch (Exception throwable) {
            return "Ошибка";
        }
    }

    @GetMapping("/change/guest/info")
    public String changeGuestInfo(Model model)
    {
        model.addAttribute("guest", new Guest());
        return "change-guest-info";

    }

    @PostMapping("/change/guest/info")
    public String changeGuestInfo(@ModelAttribute("guest") Guest guest)
    {
        try {
            adminService.changeGuestInfo(guest.getGuestId(), guest.getSurname(), guest.getName(), guest.getPatronymic(),
                    guest.getPhoneNumber(), guest.getPassport(),  guest.getDateOfBirth().getTime());
            return "redirect:/admin/getAllGuests";
        } catch (Exception throwable) {
            return "Ошибка";
        }
    }

    @GetMapping("/change/manager/info")
    public String changeManagerInfo(Model model)
    {
        model.addAttribute("manager", new Manager());
        return "change-manager-info";

    }

    @PostMapping("/change/manager/info")
    public String changeManagerInfo(@ModelAttribute("manager") Manager manager)
    {
        try {
            adminService.changeManagerInfo(manager.getManagerId(), manager.getSurname(), manager.getName(), manager.getPatronymic(),
                    manager.getPhoneNumber());
            return "redirect:/admin/getAllManagers";
        } catch (Exception throwable) {
            return "Ошибка";
        }
    }


    @GetMapping("/getPopularServices")
    public String getPopularServices(Model model)
    {
        try {
            ArrayList<Service> services = adminService.getPopularService();
            model.addAttribute("services", services);

            return "popular-services";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }



    @GetMapping("/getTotalProfit")
    public String getTotalProfit(Model model)
    {
        model.addAttribute("totalprofit", new TotalProfit());
        return "get-total-profit";

    }

    @PostMapping("/getTotalProfit")
    public String calculateTotalProfit(Model model, @ModelAttribute("totalprofit") TotalProfit totalProfit)
    {
        try {
            double profit = adminService.getTotalProfit(totalProfit.getStartDate().getTime(), totalProfit.getEndDate().getTime());
            totalProfit.setProfit(profit);
            totalProfit.setStartDate(totalProfit.getStartDate());
            model.addAttribute("totalprofit", totalProfit);
            return "total-profit";
        } catch (Exception throwable)
        {
            return "Ошибка";
        }
    }


    @GetMapping("/getUnpopularServices")
    public String getUnpopularServices(Model model)
    {
        try {
            ArrayList<Service> services = adminService.getUnpopularServices();
            model.addAttribute("services", services);

            return "all-services";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/getPopularRooms")
    public String getPopularRooms(Model model)
    {
        try {
            ArrayList<Room> rooms = adminService.getPopularRooms();
            model.addAttribute("rooms", rooms);

            return "all-rooms";
        } catch (Exception throwable) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/register/manager")
    public String registerManager(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("manager", new Manager());

        return "manager-registration";
    }

    @PostMapping("/register/manager")
    public String registerManager(@ModelAttribute("manager") Manager manager, BindingResult bindingResult, @ModelAttribute("user") User user, Model model, ModelMap modelMap)
    {
        if(bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        if(!adminService.checkData(user.getLogin(), user.getPassword(), manager.getSurname(), manager.getName(),
                manager.getPatronymic(), manager.getPhoneNumber()))
        {
            model.addAttribute("successMessage", "Поля не могут быть пустыми");
            return "manager-registration";
        }
        try
        {
            if (adminService.checkManagerLogin(user.getLogin()))
            {
                model.addAttribute("successMessage", "Пользователь с таким логином уже существует или логин введён некорректно");
                return "manager-registration";
            }
            if (adminService.checkManagerPhone(manager.getPhoneNumber()))
            {
                model.addAttribute("successMessage", "Пользователь с таким номером телефона уже существует или номер введён некорректно");
                return "manager-registration";
            }

            else
            {
                adminService.registerManager(user.getLogin(), user.getPassword(), manager.getSurname(), manager.getName(),
                        manager.getPatronymic(), manager.getPhoneNumber());
                model.addAttribute("successMessage", "Менеджер зарегистрирован");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return "redirect:/admin/getAllManagers";
    }

}

