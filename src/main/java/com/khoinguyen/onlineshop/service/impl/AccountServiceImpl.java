package com.khoinguyen.onlineshop.service.impl;

import com.khoinguyen.onlineshop.dto.account.AccountDTOCreate;
import com.khoinguyen.onlineshop.dto.account.AccountDTOResponse;
import com.khoinguyen.onlineshop.dto.account.AccountDTOUpdate;
import com.khoinguyen.onlineshop.dto.authentication.LoginDTORequest;
import com.khoinguyen.onlineshop.dto.authentication.LoginDTOResponse;
import com.khoinguyen.onlineshop.entity.Account;
import com.khoinguyen.onlineshop.exception.OnlineShopException;
import com.khoinguyen.onlineshop.mapper.AccountMapper;
import com.khoinguyen.onlineshop.repository.AccountRepository;
import com.khoinguyen.onlineshop.service.AccountService;
import com.khoinguyen.onlineshop.util.JwtTokenUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenUtil jwtTokenUtil;

    /*
    TH1: username is null -> can not create
    TH2: email is null -> can not create
    TH3: username is existed -> can not create
    TH4: email is existed -> can not create
    TH5: create account successful
     */
    @Override
    public AccountDTOResponse createAccount(AccountDTOCreate accountDTOCreate) {
        validateAccountDTOCreate(accountDTOCreate);
        Account account = AccountMapper.toAccount(accountDTOCreate);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        return AccountMapper.toAccountDTOResponse(account);
    }

    private void validateAccountDTOCreate(AccountDTOCreate accountDTOCreate) {
        if(accountDTOCreate.getUsername() == null) {
            throw OnlineShopException.badRequest("Username must be not null");
        }
        if(accountDTOCreate.getEmail() == null) {
            throw OnlineShopException.badRequest("Email must be not null");
        }
        if(accountRepository.existsByUsername(accountDTOCreate.getUsername())) {
            throw OnlineShopException.badRequest("Username is existed");
        }
        if(accountRepository.existsByEmail(accountDTOCreate.getEmail())) {
            throw OnlineShopException.badRequest("Email is existed");
        }
    }

    @Override
    public LoginDTOResponse login(LoginDTORequest loginDTORequest) {
        //get account by username
		Account account = accountRepository
                .findByUsername(loginDTORequest.getUsername())
                .orElseThrow(() -> OnlineShopException.notFound("Account not found"));

        //check password
        boolean isAuthentication = passwordEncoder
                .matches(loginDTORequest.getPassword(), account.getPassword());
		if(!isAuthentication)
            throw OnlineShopException.badRequest("Username or password is incorrect");

        //ok -> generate token
		final long ONE_DAY = 24 * 60 * 60;
		String accessToken = jwtTokenUtil
                .generateToken(AccountMapper.toTokenPayLoad(account), ONE_DAY);

        return AccountMapper.toLoginDTOResponse(account, accessToken);
    }

    @Override
    public List<AccountDTOResponse> getAllAccounts() {
        return accountRepository
                .findAll().stream()
                .map(AccountMapper::toAccountDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTOResponse getAccountById(int id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> OnlineShopException.notFound("Account not found"));
        return AccountMapper.toAccountDTOResponse(account);
    }

    @Override
    public AccountDTOResponse updateAccount(AccountDTOUpdate accountDTOUpdate, int id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> OnlineShopException.notFound("Account not found"));

        Optional<Account> accountOptional = accountRepository.checkDuplicateUsername(accountDTOUpdate.getUsername(), id);
		if(accountOptional.isPresent())
            throw OnlineShopException.badRequest("Username can not duplicate");

        account = AccountMapper.toAccount(accountDTOUpdate, account);
        account = accountRepository.save(account);
        return AccountMapper.toAccountDTOResponse(account);
    }
}
