package hu.tbs.authApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="Europe/Budapest")
    private Date lastLogin;

    private List<RoleDTO> roles;
}
