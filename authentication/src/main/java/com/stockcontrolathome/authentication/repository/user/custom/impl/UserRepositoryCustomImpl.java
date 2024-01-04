package com.stockcontrolathome.authentication.repository.user.custom.impl;

import com.stockcontrolathome.authentication.entity.User;
import com.stockcontrolathome.authentication.repository.user.UserRepository;
import com.stockcontrolathome.authentication.repository.user.custom.UserRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
