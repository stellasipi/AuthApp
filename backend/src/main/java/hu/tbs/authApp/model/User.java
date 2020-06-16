package hu.tbs.authApp.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    @ManyToMany
    @JoinTable(name="user_role",
                joinColumns = @JoinColumn(name = "userID"),
                inverseJoinColumns = @JoinColumn(name = "roleID"))
    private Set<Role> roles=new HashSet<>();

    public void addRole(Role role){
        if(roles==null) roles=new HashSet<>();
        this.roles.add(role);
        role.addUser(this);
    }
}
