package hu.tbs.authApp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, unique = true)
    @NotNull
    private String name;

    @NotNull
    private String message;

}
