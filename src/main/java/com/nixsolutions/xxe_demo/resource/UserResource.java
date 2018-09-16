package com.nixsolutions.xxe_demo.resource;

import com.nixsolutions.xxe_demo.UnmarshallingComponent;
import com.nixsolutions.xxe_demo.config.security.SignUpHandler;
import com.nixsolutions.xxe_demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource
{
  @Autowired
  private SignUpHandler signUpHandler;

  @Autowired
  private UnmarshallingComponent unmarshallingComponent;

  @PutMapping(path = "/register")
  public ResponseEntity register(@RequestBody String payload)
  {
    boolean result = signUpHandler.signUp(unmarshallingComponent.<User>unmarshall(payload));

    return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
  }
}
