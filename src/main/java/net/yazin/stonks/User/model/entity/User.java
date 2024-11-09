package net.yazin.stonks.User.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STONK_USERS")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int userId;

    private String fullName;

    private String email;

    private String password;

    private long dateJoined;

}
