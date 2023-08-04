package com.khoinguyen.onlineshop.dto.authentication;

import com.khoinguyen.onlineshop.dto.account.AccountDTOResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDTOResponse {
    String accessToken;
    AccountDTOResponse account;
}
