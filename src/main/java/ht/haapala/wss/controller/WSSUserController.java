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
 * Controller for user data. Uses JSON for input and output.
 *
 * @author Heikki Haapala
 */
@Controller
@RequestMapping(value = "users")
public class WSSUserController {

    @Autowired
    private WSSUserService userService;

    /**
     * Handles GET requests to "users/loggedin". Responds with info of the user
     * that is logged in.
     * 
     * @param principal authentication information (username)
     * @return the user info of the logged in user
     */
    @RequestMapping(value = "loggedin", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public WSSUser loggedInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        return userService.findOne(principal.getName());
    }

    /**
     * Handles GET requests to "users". Responds with a list of all users.
     * 
     * @return a list of all users
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<WSSUser> list() {
        return userService.findAll();
    }

    /**
     * Handles POST requests to "users". Creates a new user with the given data.
     * 
     * @param user user data from request body
     * @return the saved user
     */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public WSSUser create(@Valid @RequestBody WSSUser user) {
        // don't allow same username
        if (userService.findOne(user.getUsername()) != null) {
            return null;
        }
        // encode password
        String barePassword = user.getBarePassword();
        if (barePassword != null && barePassword.length() >= 8) {
            user.setPassword(encodePassword(barePassword));
        } else {
            return null;
        }
        return userService.save(user);
    }

    /**
     * Handles DELETE requests to "users/username". Deletes the user with the
     * given username.
     * 
     * @param username username from path
     */
    @RequestMapping(value = "{username}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable String username) {
        if (!username.equals("admin")) {
            userService.delete(username);
        }
    }

    // private method for encoding passwords
    private String encodePassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
