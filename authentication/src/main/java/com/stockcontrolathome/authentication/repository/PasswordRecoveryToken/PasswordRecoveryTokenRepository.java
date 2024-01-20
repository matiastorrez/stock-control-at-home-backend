package com.stockcontrolathome.authentication.repository.PasswordRecoveryToken;

import com.stockcontrolathome.authentication.entity.PasswordRecoveryToken;
import com.stockcontrolathome.authentication.enums.EPasswordRecoveryTokenState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordRecoveryTokenRepository extends JpaRepository<PasswordRecoveryToken, Long> {

    Optional<PasswordRecoveryToken> findFirstByTokenAndEmailAndState(String token, String email, EPasswordRecoveryTokenState state);
    Optional<PasswordRecoveryToken> findFirstByEmail(String email);

    void deleteByTokenAndEmailAndState(String token, String email, EPasswordRecoveryTokenState state);


}
