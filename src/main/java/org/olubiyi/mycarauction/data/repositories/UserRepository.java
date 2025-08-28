package org.olubiyi.mycarauction.data.repositories;

import org.olubiyi.mycarauction.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email); // ✅ add this
}


