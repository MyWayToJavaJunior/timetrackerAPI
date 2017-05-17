package com.touchsoft.timetracker.api.controller;

import com.touchsoft.timetracker.api.dao.UserException;
import com.touchsoft.timetracker.api.model.User;
import com.touchsoft.timetracker.api.service.UserService;
import com.touchsoft.timetracker.util.security.configure.SecurityServiceImpl;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class UsersController {

    @Resource
    private SecurityServiceImpl securityService;

    @Resource
    private UserService usersService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() throws NotFoundException {
        return usersService.getUsers();
    }

    @RequestMapping(value = "users/user/{userLogin}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable(value = "userLogin") String login) {
        User user;
        try {
            user = usersService.getUser(login);
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void authorization(@RequestParam Map<String, String> requestParam) {
        User user = new User();
        if (requestParam == null || requestParam.get("login") == null || requestParam.get("password") == null) {
            throw new IllegalArgumentException("Login and/or password not provider");
        }
        user.setLogin(requestParam.get("login"));
        user.setPassword(requestParam.get("password"));

        /*ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);*/

        //System.out.println("JSESSIONID="+session.getId());
        securityService.userAuthorization(user.getLogin(), user.getPassword());


    }

}
