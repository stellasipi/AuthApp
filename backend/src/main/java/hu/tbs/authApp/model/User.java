package hu.tbs.authApp.model;

import javax.validation.constraints.NotNull;
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

    @Column(length = 50, unique = true)
    @NotNull
    private String username;

    @NotNull
    private String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Session session;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
                joinColumns = @JoinColumn(name = "userID"),
                inverseJoinColumns = @JoinColumn(name = "roleID"))
    private Set<Role> roles=new HashSet<>();

    public void addRole(Role role){
        if(roles==null) roles=new HashSet<>();
        this.roles.add(role);
        role.addUser(this);
    }

    public void createSession(Session session){
        this.session=session;
        session.setUser(this);
    }
}
