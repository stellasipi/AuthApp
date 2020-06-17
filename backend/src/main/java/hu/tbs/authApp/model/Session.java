package hu.tbs.authApp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String jSessionId;

    private Date creationDate;

    private Date expirationDate;

    @OneToOne
    private User user;

    public void addNewSession(String jSessionId, long creationDate, long expirationTime){
        this.jSessionId=jSessionId;
        this.creationDate=new Date(creationDate);
        this.expirationDate=new Date(Long.sum(creationDate,expirationTime*1000));
    }

    public void invalidateSession(){
        this.jSessionId=null;
        this.creationDate=null;
        this.expirationDate=null;
    }
}
