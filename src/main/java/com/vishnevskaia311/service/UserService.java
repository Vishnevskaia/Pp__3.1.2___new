package com.vishnevskaia311.service;


import java.util.List;

import com.vishnevskaia311.model.User;


public interface UserService {

    User getUserByName(String name);

    List<User> index();

    User show(Long id);

    void save(User user);

    void update( User updatedUser, Long id);

    void delete(Long id);
}
