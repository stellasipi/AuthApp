package hu.tbs.authApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SessionDTO {
    private String jSessionId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="Europe/Budapest")
    private Date creationDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="Europe/Budapest")
    private Date expirationDate;
}
