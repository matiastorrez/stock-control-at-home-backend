package com.stockcontrolathome.authentication.entity;


import com.stockcontrolathome.authentication.enums.UserState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="lastname", nullable = false)
    private String lastname;

    @Column(name="email", nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "fk_user"),
            inverseJoinColumns = @JoinColumn(name = "fk_role"))
    private Set<Role> roles = new HashSet<>();

    @Column(name="password" , nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false)
    private UserState state;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updated;


}
