package com.khoinguyen.onlineshop.service;

import com.khoinguyen.onlineshop.dto.account.AccountDTOCreate;
import com.khoinguyen.onlineshop.dto.account.AccountDTOResponse;
import com.khoinguyen.onlineshop.dto.account.AccountDTOUpdate;
import com.khoinguyen.onlineshop.dto.authentication.LoginDTORequest;
import com.khoinguyen.onlineshop.dto.authentication.LoginDTOResponse;

import java.util.List;

public interface AccountService {
    public AccountDTOResponse createAccount(AccountDTOCreate accountDTOCreate);

    public LoginDTOResponse login(LoginDTORequest loginDTORequest);

    public List<AccountDTOResponse> getAllAccounts();

    public AccountDTOResponse getAccountById(int id);

    AccountDTOResponse updateAccount(AccountDTOUpdate accountDTOUpdate, int id);
}
