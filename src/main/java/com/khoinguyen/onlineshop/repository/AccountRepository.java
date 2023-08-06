package com.khoinguyen.onlineshop.repository;

import com.khoinguyen.onlineshop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Optional<Account> findByUsername(String username);

    @Query("select a from Account as  a where a.username = ?1 and a.id <> ?2")
    public Optional<Account> checkDuplicateUsername(String username, int id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
