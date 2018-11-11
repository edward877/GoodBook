package com.goodbook.book.controller.api;

import com.goodbook.book.model.CustomerDto;
import com.goodbook.book.model.UserDto;
import com.goodbook.book.model.Enum.UserRole;
import com.goodbook.book.service.interfaces.CustomerService;
import com.goodbook.book.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/registration")
    public void registration(){ }

    //TODO придумать что-то с транзакцией
    @PostMapping("/registration")
    public String registration(@ModelAttribute UserDto userDto,
                               @ModelAttribute CustomerDto customerDto,
                               ModelMap model){
        try{
            userDto.setRole(UserRole.ROLE_USER.name());
            userService.registration(userDto);
            customerDto.setUser(userDto);
            customerService.create(customerDto);
            return "login";
        }catch (Exception ex){
            System.out.println("ERROR - " + ex.getMessage());
            return "registration";
        }
    }
}
