package com.khoinguyen.onlineshop.controller;

import com.khoinguyen.onlineshop.dto.account.AccountDTOCreate;
import com.khoinguyen.onlineshop.dto.account.AccountDTOResponse;
import com.khoinguyen.onlineshop.dto.account.AccountDTOUpdate;
import com.khoinguyen.onlineshop.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public List<AccountDTOResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountDTOResponse getAccountById(@PathVariable int id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/{id}")
    public AccountDTOResponse updateAccount(@RequestBody AccountDTOUpdate accountDTOUpdate,
                                            @PathVariable int id) {
        return accountService.updateAccount(accountDTOUpdate, id);
    }
}
