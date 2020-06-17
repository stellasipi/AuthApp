package hu.tbs.authApp.web;

import hu.tbs.authApp.dto.UserDTO;
import hu.tbs.authApp.model.Page;
import hu.tbs.authApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity login(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("logout") //?????
    public void logout(){

    }

    @GetMapping("user/details")
    public UserDTO getUserDetails(Principal principal){
        return userService.getUserDetails(principal);
    }

    @GetMapping("user/pages")
    public List<Page> getUserPages(Principal principal){
        return userService.getUserPages(principal);
    }

}
