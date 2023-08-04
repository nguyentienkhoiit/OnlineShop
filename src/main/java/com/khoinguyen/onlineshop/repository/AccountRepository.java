package com.khoinguyen.onlineshop.repository;

import com.khoinguyen.onlineshop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Optional<Account> findByUsername(String username);
}
