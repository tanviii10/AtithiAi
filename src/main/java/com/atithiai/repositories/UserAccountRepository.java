package com.atithiai.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atithiai.entities.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{

	Optional<UserAccount> findByPhone(String phone);
}
