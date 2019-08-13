package ru.aplana.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor @AllArgsConstructor @ToString @Builder
@Entity
public class User {

    @Id
    @Getter @Setter
    Long id;

    @Getter @Setter
    String name;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    Set<Project> projects;
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
