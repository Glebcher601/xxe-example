package com.nixsolutions.xxe_demo.config.security;

import com.nixsolutions.xxe_demo.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, SignUpHandler
{
  private static final Map<String, String> DB_IMITATION = new ConcurrentHashMap<String, String>();

  static
  {
    DB_IMITATION.put("user1", "password1");
    DB_IMITATION.put("user2", "password2");
    DB_IMITATION.put("user3", "password3");
    DB_IMITATION.put("user4", "password4");
    DB_IMITATION.put("user5", "password5");
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException
  {
    String password = DB_IMITATION.get(authentication.getName());
    if (password != null && authentication.getCredentials().equals(password))
    {
      Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
      return new UsernamePasswordAuthenticationToken(authentication.getName(), password, authorities);
    }

    throw new BadCredentialsException("Authentication invalid");
  }

  @Override
  public boolean supports(Class<?> aClass)
  {
    return aClass.equals(UsernamePasswordAuthenticationToken.class);
  }

  @Override
  public boolean signUp(User user)
  {
    if (DB_IMITATION.containsKey(user.getLogin()))
    {
      return false;
    }

    DB_IMITATION.put(user.getLogin(), user.getPassword());

    return true;
  }
}
