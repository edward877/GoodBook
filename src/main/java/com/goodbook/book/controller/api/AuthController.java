package com.goodbook.book.controller.api;

import com.goodbook.book.model.UserDto;
import com.goodbook.book.model.Enum.UserRole;
import com.goodbook.book.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/")
//@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("registration")
    public Map registration(@ModelAttribute UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        try{
            userDto.setRole(UserRole.ROLE_USER);
            userService.registration(userDto);
            map.put("User",userDto);
        }catch (Exception ex) {
            map.put("Error", ex.getMessage());
        }
        return map;
    }

    @PostMapping("login/success")
    public Map loginSuccess() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @PostMapping("login/failure")
    public Map loginFailure() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        return map;
    }
}
