package com.valdir.app.security;

import com.valdir.app.models.enums.Perfil;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Essa classe serve para fornecer informações básicas do usuário
 */
public class UserSS implements UserDetails {

    @Getter
    private Integer id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
