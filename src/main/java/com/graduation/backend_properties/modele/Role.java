package com.graduation.backend_properties.modele;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

//import java.security.Permission;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.graduation.backend_properties.modele.Permission.ADMIN_DELETE;
import static com.graduation.backend_properties.modele.Permission.ADMIN_READ;
import static com.graduation.backend_properties.modele.Permission.ADMIN_CREATE;
import static com.graduation.backend_properties.modele.Permission.ADMIN_UPDATE;
import static com.graduation.backend_properties.modele.Permission.MANAGER_UPDATE;
import static com.graduation.backend_properties.modele.Permission.MANAGER_CREATE;
import static com.graduation.backend_properties.modele.Permission.MANAGER_DELETE;
import static com.graduation.backend_properties.modele.Permission.MANAGER_READ;

@RequiredArgsConstructor
public enum Role {
    User(Collections.emptySet()),
    ANNONCEUR(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),
    ACHETEUR(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;

    }
}
