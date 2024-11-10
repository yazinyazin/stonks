package net.yazin.stonks.User.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.yazin.stonks.Common.model.enums.UserRole;

@AllArgsConstructor
@Getter
public class UserDTO {

    @NotNull private String fullName;

    @NotNull private String username;

    @NotNull private String password;

    @NotNull private UserRole userRole;

    @NotNull @Email private String email;

}
