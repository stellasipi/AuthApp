package hu.tbs.authApp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, unique = true)
    @NotNull
    private String name;

    @Column(length = 50, unique = true)
    @NotNull
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users=new HashSet<>();

    public void addUser(User user){
        if(users==null) users=new HashSet<>();
        this.users.add(user);
    }
}
