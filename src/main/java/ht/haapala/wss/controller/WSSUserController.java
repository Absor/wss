package ht.haapala.wss.controller;

import ht.haapala.wss.data.WSSUser;
import ht.haapala.wss.service.WSSUserService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = "users", method = RequestMethod.GET)
public class WSSUserController {

    //              String password = "admin";
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(password);
//        System.out.println(hashedPassword);
    @Autowired
    private WSSUserService userService;

    @RequestMapping(value = "loggedin", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public WSSUser loggedInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        return userService.findOne(principal.getName());
    }
}
