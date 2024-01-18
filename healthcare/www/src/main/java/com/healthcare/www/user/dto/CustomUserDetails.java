package com.healthcare.www.user.dto;

import com.healthcare.www.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;



public class CustomUserDetails implements UserDetails {

  private final User user;

  public CustomUserDetails(User user) {
    this.user=user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();
    collection.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return user.getUserRole();
      }
    });

    return collection;
  }

  @Override
  public String getPassword() {
    // 계정 패스워드
    return user.getUserPassword();
  }

  @Override
  public String getUsername() {
    // 계정 ID
    return user.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    // 계정이 만료 여부
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // 계정 잠겨있는지 여부
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // 계정 패스워드 만료 여부
    return true;
  }

  @Override
  public boolean isEnabled() {
    // 사용 가능 계정인지 여부
    return true;
  }
}
