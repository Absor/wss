package ht.haapala.wss.controller;

import ht.haapala.wss.data.WSSUser;
import ht.haapala.wss.service.WSSUserService;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Heikki Haapala
 */
@Controller
@RequestMapping(value = "users")
public class WSSUserController {

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

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<WSSUser> list() {
        return userService.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public WSSUser create(@Valid @RequestBody WSSUser user) {
        if (userService.findOne(user.getUsername()) != null) {
            return null;
        }
        String barePassword = user.getBarePassword();
        if (barePassword != null && barePassword.length() >= 8) {
            user.setPassword(encodePassword(barePassword));
        } else {
            return null;
        }
        return userService.save(user);
    }

    @RequestMapping(value = "{username}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable String username) {
        if (!username.equals("admin")) {
            userService.delete(username);
        }
    }

    private String encodePassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
