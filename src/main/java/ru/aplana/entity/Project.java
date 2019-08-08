package ru.aplana.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor @AllArgsConstructor @ToString @Builder
@Entity
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    String name;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> users;


    public Project(String name) {
        this.name = name;
    }
}
