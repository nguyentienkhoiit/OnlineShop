package com.khoinguyen.onlineshop.service;

import com.khoinguyen.onlineshop.dto.account.AccountDTOCreate;
import com.khoinguyen.onlineshop.dto.account.AccountDTOResponse;
import com.khoinguyen.onlineshop.dto.authentication.LoginDTORequest;
import com.khoinguyen.onlineshop.dto.authentication.LoginDTOResponse;

public interface AccountService {
    public AccountDTOResponse createAccount(AccountDTOCreate accountDTOCreate);

    public LoginDTOResponse login(LoginDTORequest loginDTORequest);
}
