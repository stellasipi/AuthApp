package hu.tbs.authApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    private Long id;
    private String name;
    private String description;
}
