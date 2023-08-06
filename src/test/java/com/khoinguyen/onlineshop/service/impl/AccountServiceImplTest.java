package com.khoinguyen.onlineshop.service.impl;

import com.khoinguyen.onlineshop.dto.account.AccountDTOCreate;
import com.khoinguyen.onlineshop.dto.account.AccountDTOResponse;
import com.khoinguyen.onlineshop.entity.Account;
import com.khoinguyen.onlineshop.exception.OnlineShopException;
import com.khoinguyen.onlineshop.repository.AccountRepository;
import com.khoinguyen.onlineshop.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
//    int sum(int a, int b) {
//        return a + b;
//    }
//
//    @Test
//    void sum_5Add10_Return_15() {
//        int a = 5;
//        int b = 10;
//        Assertions.assertEquals(15, sum(a, b));
//    }

    @InjectMocks //dai dien cho chu the muon test
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtTokenUtil jwtTokenUtil;
    @Test
    void createAccount_with_username_is_null() {
        //GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username(null)
                .password("123")
                .email("email")
                .build();

        //WHEN

        //THEN
        OnlineShopException actualThrow = Assertions
                .assertThrows(OnlineShopException.class, () -> accountService.createAccount(accountDTOCreate));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, actualThrow.getStatus());
        Assertions.assertEquals("Username must be not null", actualThrow.getError().getMessage());
        Mockito.verify(accountRepository, Mockito.never()).save(any(Account.class));
    }

    @Test
    void createAccount_with_email_is_null() {
        //GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username("quan")
                .password("123")
                .email(null)
                .build();

        //WHEN


        //THEN
        OnlineShopException actualThrow = Assertions
                .assertThrows(OnlineShopException.class, () -> accountService.createAccount(accountDTOCreate));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, actualThrow.getStatus());
        Assertions.assertEquals("Email must be not null", actualThrow.getError().getMessage());
        Mockito.verify(accountRepository, Mockito.never()).save(any(Account.class));
    }

    @Test
    void createAccount_with_username_is_existed() {
        //GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username("existedUsername")
                .password("123")
                .email("email")
                .build();

        //WHEN
        Mockito.when(accountRepository.existsByUsername("existedUsername")).thenReturn(true);

        //THEN
        OnlineShopException actualThrow = Assertions
                .assertThrows(OnlineShopException.class, () -> accountService.createAccount(accountDTOCreate));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, actualThrow.getStatus());
        Assertions.assertEquals("Username is existed", actualThrow.getError().getMessage());
        Mockito.verify(accountRepository, Mockito.never()).save(any(Account.class));
    }

    @Test
    void createAccount_with_email_is_existed() {
        //GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username("quan")
                .password("123")
                .email("existedEmail")
                .build();

        //WHEN
        Mockito.when(accountRepository.existsByUsername("quan")).thenReturn(false);
        Mockito.when(accountRepository.existsByEmail("existedEmail")).thenReturn(true);

        //THEN
        OnlineShopException actualThrow = Assertions
                .assertThrows(OnlineShopException.class, () -> accountService.createAccount(accountDTOCreate));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, actualThrow.getStatus());
        Assertions.assertEquals("Email is existed", actualThrow.getError().getMessage());
        Mockito.verify(accountRepository, Mockito.never()).save(any(Account.class));
    }

    @Test
    void createAccount_success() {
        //GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username("quan")
                .password("123")
                .email("email")
                .build();

        Account savedAccount = Account.builder()
                .id(1)
                .username("quan")
                .password("123")
                .email("email")
                .build();

        //WHEN
        Mockito.when(accountRepository.existsByUsername("quan")).thenReturn(false);
        Mockito.when(accountRepository.existsByEmail("email")).thenReturn(false);
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        //THEN
        AccountDTOResponse actualAccountDTOResponse = accountService.createAccount(accountDTOCreate);
        Mockito.verify(accountRepository, Mockito.times(1)).save(any(Account.class));
        Assertions.assertEquals(1, actualAccountDTOResponse.getId());
        Assertions.assertEquals("quan", actualAccountDTOResponse.getUsername());
        Assertions.assertEquals("email", actualAccountDTOResponse.getEmail());

    }
}