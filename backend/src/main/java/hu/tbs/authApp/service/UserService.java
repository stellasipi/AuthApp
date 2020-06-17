package hu.tbs.authApp.service;

import hu.tbs.authApp.dto.SessionDTO;
import hu.tbs.authApp.dto.UserDTO;
import hu.tbs.authApp.mapper.SessionMapper;
import hu.tbs.authApp.mapper.UserMapper;
import hu.tbs.authApp.model.Page;
import hu.tbs.authApp.model.Role;
import hu.tbs.authApp.model.Session;
import hu.tbs.authApp.model.User;
import hu.tbs.authApp.repository.PageRepository;
import hu.tbs.authApp.repository.SessionRepository;
import hu.tbs.authApp.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final SessionMapper sessionMapper;

    public UserService(){
        userMapper= Mappers.getMapper(UserMapper.class);
        sessionMapper=Mappers.getMapper(SessionMapper.class);
    }

    public UserDTO getUserDetails(Principal principal){
        User user=userRepository.findByUsername(principal.getName());
        UserDTO userDTO=userMapper.userToUserDTO(user);
        userDTO.setLastLogin(user.getSession().getCreationDate());
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

    public SessionDTO login(HttpServletRequest request, User userFromBody){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userFromBody.getUsername(), userFromBody.getPassword());
        Authentication authentication = authManager.authenticate(authenticationToken);
        User authenticatedUser=userRepository.findByUsername(userFromBody.getUsername());
        Session userSession=authenticatedUser.getSession();

        if(!isCurrentSessionValid(userSession)) {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);

            userSession.addNewSession(session.getId(), session.getCreationTime(), session.getMaxInactiveInterval());
            sessionRepository.flush();
            LOGGER.info("New session has been created");
        }

        return sessionMapper.sessionToSessionDTO(userSession);
    }

    public Boolean isCurrentSessionValid(Session session){
        Date now=new Date(System.currentTimeMillis());
        if(session.getJSessionId()==null || now.after(session.getExpirationDate())){
            return false;
        }else {
            return true;
        }

    }

    public void logout(HttpSession session,Principal principal){
        session.invalidate();
        User user=userRepository.findByUsername(principal.getName());
        user.getSession().invalidateSession();
        sessionRepository.flush();
        LOGGER.info("User succesfully logged out.");
    }

}
