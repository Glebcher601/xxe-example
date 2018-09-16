package com.nixsolutions.xxe_demo.config.security;

import com.nixsolutions.xxe_demo.model.User;

public interface SignUpHandler
{
  boolean signUp(User user);
}
