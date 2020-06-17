package hu.tbs.authApp.web;

import hu.tbs.authApp.model.Page;
import hu.tbs.authApp.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pages/")
public class PageController {

    @Autowired
    private PageRepository pageRepository;

    @GetMapping("contentEditor")
    public Page contentEditor (){
        return pageRepository.findByName("contentEditor");
    }

    @GetMapping("loggedInUser")
    public Page loggedInUser (){
        return pageRepository.findByName("loggedInUser");
    }

    @GetMapping("admin")
    public Page admin (){
        return pageRepository.findByName("admin");
    }
}
