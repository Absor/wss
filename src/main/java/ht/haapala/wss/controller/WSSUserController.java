package ht.haapala.wss.controller;

import ht.haapala.wss.service.WSSUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Heikki Haapala
 */
@Controller
@RequestMapping(value = "wss/users")
public class WSSUserController {
    
    @Autowired
    private WSSUserService userService;
    
    @RequestMapping(value = "*", method = RequestMethod.GET)
    @ResponseBody
    public String testi() {
//              String password = "admin";
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(password);
//        System.out.println(hashedPassword);
        return "Testi";
    }
}
