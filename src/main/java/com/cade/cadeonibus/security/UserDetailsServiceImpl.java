package com.cade.cadeonibus.security;

import com.cade.cadeonibus.domain.User;
import com.cade.cadeonibus.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(login)
      .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
    return new UserDetailsImpl(user);
  }
}
