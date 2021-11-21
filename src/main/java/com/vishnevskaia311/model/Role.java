package com.vishnevskaia311.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;


    public Role(Long id) {
        this.id = id;
    }

    public Role(String role) {
        this.role = role;
    }

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        role
                .replace("[", "")
                .replace("]", "");
        return role;

    }

    public String getRole() {
        return role.replace("[", "")
                .replace("]", "");

    }


    @Override
    public String getAuthority() {
        return role;
    }


}
