package store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import store.validation.AdvanceInfo;
import store.validation.PasswordMatch;
import store.validation.ValidPassword;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
//@MappedSuperclass
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatch(groups = AdvanceInfo.class, message = "password and confirmed password don't match")
public class User implements UserDetails {
    @Id
    @Email
    private String email;
    @NotBlank(message = "User first Name is required")
    private String fName;
    @NotBlank(message = "User Last Name is required")
    private String lName;

    @NotBlank(message = "User Password is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ValidPassword(groups = AdvanceInfo.class)
    private String password;

    @NotBlank(message = "User Confirm Password is required")
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private Cart cart;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
