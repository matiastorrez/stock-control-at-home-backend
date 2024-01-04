package com.stockcontrolathome.authentication.repository.user.custom;

import com.stockcontrolathome.authentication.entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {

    User createUser(User user);

    Optional<User> getUserByEmail(String email);

}
