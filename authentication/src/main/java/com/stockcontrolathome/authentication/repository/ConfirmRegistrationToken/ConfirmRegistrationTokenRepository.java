package com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken;

import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.EConfirmRegistrationTokenState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmRegistrationTokenRepository extends JpaRepository<ConfirmRegistrationToken, Long> {

    Optional<ConfirmRegistrationToken> findFirstByTokenAndEmailAndState(String token, String email, EConfirmRegistrationTokenState state);
    Optional<ConfirmRegistrationToken> findFirstByEmail(String email);

    void deleteByTokenAndEmailAndState(String token, String email, EConfirmRegistrationTokenState state);

}
