package com.khoinguyen.onlineshop.mapper;

import com.khoinguyen.onlineshop.dto.account.AccountDTOCreate;
import com.khoinguyen.onlineshop.dto.account.AccountDTOResponse;
import com.khoinguyen.onlineshop.dto.account.AccountDTOUpdate;
import com.khoinguyen.onlineshop.dto.authentication.LoginDTOResponse;
import com.khoinguyen.onlineshop.entity.Account;
import com.khoinguyen.onlineshop.model.TokenPayload;

public class AccountMapper {
    public static Account toAccount(AccountDTOCreate accountDTOCreate) {
        return Account.builder()
                .username(accountDTOCreate.getUsername())
                .password(accountDTOCreate.getPassword())
                .email(accountDTOCreate.getEmail())
                .build();
    }

    public static Account toAccount(AccountDTOUpdate accountDTOUpdate, Account account) {
        return Account.builder()
                .id(account.getId())
                .email(account.getEmail())
                .username(accountDTOUpdate.getUsername())
                .password(accountDTOUpdate.getPassword())
                .build();
    }

    public static AccountDTOResponse toAccountDTOResponse(Account account) {
        return AccountDTOResponse.builder()
                .id(account.getId())
                .username(account.getUsername())
                .email(account.getEmail())
                .build();
    }

    public static TokenPayload toTokenPayLoad(Account account) {
        return TokenPayload.builder()
                .accountId(account.getId())
                .username(account.getUsername())
                .build();
    }

    public static LoginDTOResponse toLoginDTOResponse(Account account, String accessToken) {
        return LoginDTOResponse.builder()
                .account(toAccountDTOResponse(account))
                .accessToken(accessToken)
                .build();
    }
}
