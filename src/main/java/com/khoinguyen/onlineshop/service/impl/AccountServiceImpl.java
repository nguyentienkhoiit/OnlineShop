package com.khoinguyen.onlineshop.service.impl;

import com.khoinguyen.onlineshop.dto.account.AccountDTOCreate;
import com.khoinguyen.onlineshop.dto.account.AccountDTOResponse;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenUtil jwtTokenUtil;

    @Override
    public AccountDTOResponse createAccount(AccountDTOCreate accountDTOCreate) {
        Account account = AccountMapper.toAccount(accountDTOCreate);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        return AccountMapper.toAccountDTOResponse(account);
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
}
