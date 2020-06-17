package hu.tbs.authApp.service;

import hu.tbs.authApp.dto.UserDTO;
import hu.tbs.authApp.mapper.UserMapper;
import hu.tbs.authApp.model.Page;
import hu.tbs.authApp.model.Role;
import hu.tbs.authApp.model.User;
import hu.tbs.authApp.repository.PageRepository;
import hu.tbs.authApp.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private final UserMapper userMapper;

    public UserService(){
        userMapper= Mappers.getMapper(UserMapper.class);
    }

    public UserDTO getUserDetails(Principal principal){
        User user=userRepository.findByUsername(principal.getName());
        UserDTO userDTO=userMapper.userToUserDTO(user);
        userDTO.setLastLogin(new Date(System.currentTimeMillis()));
        return userDTO;
    }

    public List<Page> getUserPages(Principal principal){
        User user=userRepository.findByUsername(principal.getName());
        List<Page> pages=new ArrayList<>();
        for(Role role:user.getRoles()){
            if(role.getName().equals("ADMIN")){
                pages.add(pageRepository.findByName("admin"));
            }

            if(role.getName().equals("EDITOR")){
                pages.add(pageRepository.findByName("contentEditor"));
            }

            if(role.getName().equals("USER")){
                pages.add(pageRepository.findByName("loggedInUser"));
            }
        }
        return pages;
    }
}
