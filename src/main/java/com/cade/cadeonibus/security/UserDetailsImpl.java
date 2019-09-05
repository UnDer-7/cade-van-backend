package com.cade.cadeonibus.security;

import com.cade.cadeonibus.domain.User;
import com.cade.cadeonibus.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 701149485120530029L;

  private Integer id;
  private String email;
  private String senha;
  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl() {
  }

  public UserDetailsImpl(User user) {
    this.id = Math.toIntExact(user.getId());
    this.email = user.getLogin();
    this.senha = user.getPasswordHash();
    this.authorities = user.getPerfis().stream().map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao())).collect(Collectors.toList());
  }

  public Integer getId() {
    return id;
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
