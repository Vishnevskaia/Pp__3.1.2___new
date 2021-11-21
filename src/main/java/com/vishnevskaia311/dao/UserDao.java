package com.vishnevskaia311.dao;


import com.vishnevskaia311.model.User;

import java.util.List;

public interface UserDao {

    List<User> index();


    User getUserByName(String name);

    User show(Long id);

    void save(User user);

    void update( User updatedUser, Long id);


    void delete(Long id);


}
