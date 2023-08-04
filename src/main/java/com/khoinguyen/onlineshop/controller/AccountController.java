package com.khoinguyen.onlineshop.controller;

import com.khoinguyen.onlineshop.dto.account.AccountDTOCreate;
import com.khoinguyen.onlineshop.dto.account.AccountDTOResponse;
import com.khoinguyen.onlineshop.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.khoinguyen.onlineshop.util.Constant.API_VERSION;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(API_VERSION+"/accounts")
public class AccountController {
    AccountService accountService;

    @PostMapping("")
    public AccountDTOResponse createAccount(@RequestBody AccountDTOCreate accountDTOCreate) {
        return accountService.createAccount(accountDTOCreate);
    }
}
