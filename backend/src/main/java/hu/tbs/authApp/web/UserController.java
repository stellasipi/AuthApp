package hu.tbs.authApp.web;

import hu.tbs.authApp.dto.SessionDTO;
import hu.tbs.authApp.dto.UserDTO;
import hu.tbs.authApp.model.Page;
import hu.tbs.authApp.model.User;
import hu.tbs.authApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<SessionDTO> login(HttpServletRequest request, @RequestBody User user) {
        SessionDTO session=userService.login(request, user);
        if(session!=null) {
            return ResponseEntity.ok(session);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("user/logout")
    public ResponseEntity logout(HttpSession session,Principal principal) {
        userService.logout(session,principal);
        return ResponseEntity.noContent().build();
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
