package com.musicapplication.musicapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(name = "email_UNIQUE_ID",columnNames = "email")
})
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String userName;
    private Integer mobileNumber;
    @Column(nullable = false,name = "email")
    private String email;

    private String role;
    @Column(nullable = false,length = 20)
    private String password;

    private String address;
    private boolean isPremium;

}
