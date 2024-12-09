//package com.hexaware.amazecare.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hexaware.amazecare.service.UserService;
//
//@RestController
//public class PasswordUpdateController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/update-passwords")
//    public String updatePasswords() {
//        userService.updatePasswords();
//        return "Passwords updated successfully!";
//    }
//}
package com.hexaware.amazecare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.service.UserService;

@RestController
public class PasswordUpdateController {

    @Autowired
    private UserService userService;

    @GetMapping("/update-passwords")
    public String updatePasswords() {
        userService.updatePasswords();
        return "Passwords updated successfully!";
    }
}


