package hu.tbs.authApp.web;

import hu.tbs.authApp.dto.PageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pages")
public class PageController {

    @GetMapping("/contentEditor")
    public PageDTO contentEditor (){
        PageDTO newPage=new PageDTO();
        newPage.setMessage("Ez a tartalomszerkesztők aloldala.");
        return newPage;
    }

    @GetMapping("/loggedInUser")
    public PageDTO loggedInUser (){
        PageDTO newPage=new PageDTO();
        newPage.setMessage("Ez a bejelentkezett felhasználók aloldala.");
        return newPage;
    }

    @GetMapping("/admin")
    public PageDTO admin (){
        PageDTO newPage=new PageDTO();
        newPage.setMessage("Ez a adminisztrátorok aloldala.");
        return newPage;
    }
}
