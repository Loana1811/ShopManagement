package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // 👇 Chỉ định đúng tên cột fullName trong DB
    @Column(name = "full_name")
    private String fullName;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String address;
    private String phone;

    @Column(name = "status")
    private String status = "ACTIVE";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles;
}
