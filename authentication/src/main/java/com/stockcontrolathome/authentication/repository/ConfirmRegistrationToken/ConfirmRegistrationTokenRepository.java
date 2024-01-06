package com.stockcontrolathome.authentication.repository.ConfirmRegistrationToken;

import com.stockcontrolathome.authentication.entity.ConfirmRegistrationToken;
import com.stockcontrolathome.authentication.enums.ConfirmRegistrationTokenState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmRegistrationTokenRepository extends JpaRepository<ConfirmRegistrationToken, Long> {

    Optional<ConfirmRegistrationToken> findFirstByTokenAndEmailAndState(String token, String email, ConfirmRegistrationTokenState state);

    void deleteByTokenAndEmailAndState(String token, String email, ConfirmRegistrationTokenState state);

}
