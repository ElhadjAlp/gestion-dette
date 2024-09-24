
package com.ism.services.Impl;

import java.util.List;
import com.ism.data.entites.User;
import com.ism.data.repository.UserRepository;
import com.ism.services.UserService;

public class UserServiceImpl implements UserService {

  private UserRepository userRepository;


  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void createUser(User user) {
    userRepository.insert(user);
  }

  @Override
  public List<User> findAllUser() {
    return userRepository.selectAll();
  }

}
