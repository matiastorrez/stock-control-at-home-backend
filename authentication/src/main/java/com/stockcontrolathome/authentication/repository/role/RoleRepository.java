package com.stockcontrolathome.authentication.repository.role;

import com.stockcontrolathome.authentication.entity.Role;
import com.stockcontrolathome.authentication.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByeRole(ERole eRole);

}
