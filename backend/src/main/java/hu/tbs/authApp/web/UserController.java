package hu.tbs.authApp.web;

import hu.tbs.authApp.dto.UserDTO;
import hu.tbs.authApp.model.Page;
import hu.tbs.authApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public void login(){

    }

    @PostMapping("/logout") //?????
    public void logout(){

    }

    @GetMapping("/user/{id}/details")
    public UserDTO getUserDetails(@PathVariable Long id){
        return userService.getUserDetails(id);
    }

    @GetMapping("/user/{id}/pages")
    public List<Page> getUserPages(@PathVariable Long id){
        return userService.getUserPages(id);
    }

}
