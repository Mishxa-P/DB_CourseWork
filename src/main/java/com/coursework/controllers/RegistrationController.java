package com.coursework.controllers;

import com.coursework.dto.Guest;
import com.coursework.dto.User;
import com.coursework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class RegistrationController
{

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

   @GetMapping("/register")
    public String registration(Model model)
   {
        model.addAttribute("user", new User());
        model.addAttribute("guest", new Guest());

        return "guest-registration";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("guest") Guest guest, BindingResult bindingResult, @ModelAttribute("user") User user, Model model, ModelMap modelMap)
    {
        if(bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        if(!userService.checkData(user.getLogin(), user.getPassword(), guest.getSurname(), guest.getName(),
                guest.getPatronymic(), guest.getPhoneNumber(), guest.getPassport()))
        {
            model.addAttribute("successMessage", "Поля не могут быть пустыми");
            return "guest-registration";
        }

        if (guest.getDateOfBirth()== null)
        {
            model.addAttribute("successMessage", "Поля не могут быть пустыми");
            return "guest-registration";
        }
        try
        {

            if (userService.checkUserLogin(user.getLogin()))
            {
                model.addAttribute("successMessage", "Пользователь с таким логином уже существует или логин введён некорректно");
                return "guest-registration";
            }
            if (userService.checkUserPhone(guest.getPhoneNumber()))
            {
                model.addAttribute("successMessage", "Пользователь с таким номером телефона уже существует или номер введён некорректно");
                return "guest-registration";
            }
            if (userService.checkUserPassport(guest.getPassport()))
            {
                model.addAttribute("successMessage", "Пользователь с таким паспортом уже существует или паспорт введён некорректно");
                return "guest-registration";
            }

            else
            {
                userService.guestRegistration(user.getLogin(), user.getPassword(), guest.getSurname(), guest.getName(),
                        guest.getPatronymic(), guest.getPhoneNumber(), guest.getPassport(), guest.getDateOfBirth().getTime());
                model.addAttribute("successMessage", "Пользователь зарегистрирован");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return "redirect:/login";
    }
}
