package com.stockcontrolathome.authentication.service.impl;

import com.stockcontrolathome.authentication.dto.user.request.ModifyPasswordRequest;
import com.stockcontrolathome.authentication.dto.user.request.RegisterUserRequest;
import com.stockcontrolathome.authentication.dto.user.response.UserInformationResponse;
import com.stockcontrolathome.authentication.entity.Role;
import com.stockcontrolathome.authentication.entity.User;
import com.stockcontrolathome.authentication.enums.ERole;
import com.stockcontrolathome.authentication.enums.UserState;
import com.stockcontrolathome.authentication.exception.ExistingEmailException;
import com.stockcontrolathome.authentication.exception.NeedToConfirmException;
import com.stockcontrolathome.authentication.exception.NonUserWithThisEmailException;
import com.stockcontrolathome.authentication.mapper.RoleMapper;
import com.stockcontrolathome.authentication.mapper.UserMapper;
import com.stockcontrolathome.authentication.repository.user.custom.UserRepositoryCustom;
import com.stockcontrolathome.authentication.service.RoleService;
import com.stockcontrolathome.authentication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryCustom userRepositoryCustom;
    private final RoleService roleService;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {

        User newUser = this.userMapper.registerUserRequestToUserEntity(registerUserRequest);

        userRepositoryCustom.getUserByEmail(newUser.getEmail()).ifPresent(userObtained -> {
            if(userObtained.getState().equals(UserState.FALTA_CONFIRMAR_REGISTRO)){
                throw new NeedToConfirmException( userObtained.getEmail() + " ya fue registrado verifique su casilla de mails para confirmar");
            }
            throw new ExistingEmailException("El email " + userObtained.getEmail() + " ya existe");
        });
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        Role roleUser = this.roleMapper.roleResponseToRoleEntity(roleService.getRoleByERole(ERole.ROLE_USER));
        newUser.getRoles().add(roleUser);
        newUser.setState(UserState.FALTA_CONFIRMAR_REGISTRO);


        userRepositoryCustom.createUser(newUser);
    }

    @Override
    public void modifyUserToConfirmRegister(String email) {
        User userObtained = userRepositoryCustom.getUserByEmail(email).orElseThrow(() -> new NonUserWithThisEmailException("No existe un usuario vinculado con el email: "+ email));
        userObtained.setState(UserState.REGISTRADO);
        this.userRepositoryCustom.createUser(userObtained);
    }

    @Override
    public UserInformationResponse getUserByEmail(String email) {
        User userObtained = userRepositoryCustom.getUserByEmail(email).orElseThrow(() -> new NonUserWithThisEmailException("No existe un usuario vinculado con el email: " + email));
        return this.userMapper.userEntityToUserInformationResponse(userObtained);
    }

    @Override
    @Transactional
    public void modifyPassword(ModifyPasswordRequest modifyPasswordRequest) {

        String newEncryptedPassword = this.passwordEncoder.encode(modifyPasswordRequest.getNewPassword());

        User userObtained = userRepositoryCustom.getUserByEmail(modifyPasswordRequest.getEmail()).orElseThrow(() -> new NonUserWithThisEmailException("No existe un usuario vinculado con el email: " + modifyPasswordRequest.getEmail()));

        userObtained.setPassword(newEncryptedPassword);

        this.userRepositoryCustom.createUser(userObtained);


    }

}
