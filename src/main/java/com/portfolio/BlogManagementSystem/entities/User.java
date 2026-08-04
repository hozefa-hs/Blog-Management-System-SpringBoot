package com.portfolio.BlogManagementSystem.entities;

import com.portfolio.BlogManagementSystem.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Getter
@Setter
@Builder

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Blog> blog;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comment;


    //This method tells spring that which authority (roles & permissions) does this current user has.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        //Setting SimpleGrantedAuthority for each role.
        //First add a role.
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        //second add permissions
        //each role can have multiple permissions
        Set<SimpleGrantedAuthority> permissions = role.getPermissions()
                .stream()                   //creating a stream because each permission we need to convert into SimpleGrantedAuthority.
                //
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());

        //permissions associated with the current role will  be added.
        authorities.addAll(permissions);

        //this will return the current role and permissions associated with the current role.
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
