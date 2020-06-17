package hu.tbs.authApp.mapper;

import hu.tbs.authApp.dto.SessionDTO;
import hu.tbs.authApp.model.Session;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionDTO sessionToSessionDTO(Session session);
    Session sessionDTOtoSession(SessionDTO sessionDTO);
}
