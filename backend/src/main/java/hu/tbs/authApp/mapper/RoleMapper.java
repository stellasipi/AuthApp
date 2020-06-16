package hu.tbs.authApp.mapper;

import hu.tbs.authApp.dto.RoleDTO;
import hu.tbs.authApp.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOtoRole(RoleDTO roleDTO);
}
