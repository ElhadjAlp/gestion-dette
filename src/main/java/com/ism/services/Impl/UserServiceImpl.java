
package com.ism.services.Impl;

import java.util.List;
import com.ism.data.entites.User;
import com.ism.data.enums.RoleEnum;
import com.ism.data.repository.UserRepository;
import com.ism.services.UserService;

public class UserServiceImpl implements UserService {

  private UserRepository userRepository;


  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
public void createUser(User user) {
    if (user.getRole() == null) {
        user.setRole(RoleEnum.BOUTIQUIER);
    }
    userRepository.insert(user);
}


  @Override
  public List<User> findAllUser() {
    return userRepository.selectAll();
  }

  @Override
  public User authenticate(String login, String password) {

      User user = userRepository.selectByLogin(login);
      if (user != null) {
          if (user.getPassword().equals(password)) {
              if (user.getRole() == null) {
                  user.setRole(RoleEnum.DEFAULT_ROLE); 
              }
              return user;
          }
      }
      return null; 
  }
  



}
