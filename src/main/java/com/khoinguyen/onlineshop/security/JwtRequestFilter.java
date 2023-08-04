package com.khoinguyen.onlineshop.security;

import com.khoinguyen.onlineshop.entity.Account;
import com.khoinguyen.onlineshop.mapper.AccountMapper;
import com.khoinguyen.onlineshop.model.TokenPayload;
import com.khoinguyen.onlineshop.repository.AccountRepository;
import com.khoinguyen.onlineshop.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtRequestFilter extends OncePerRequestFilter {
    JwtTokenUtil jwtTokenUtil;
    AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
		String requestTokenHeader = request.getHeader("Authorization");

        String token = null;
        TokenPayload tokenPayLoad = null;
        System.out.println(requestTokenHeader);
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			token = requestTokenHeader.split(" ")[1];
            try {
                tokenPayLoad = jwtTokenUtil.getTokenPayload(token);
            }
            catch (ExpiredJwtException ex) {
                System.out.println("Token is expired");
            }
        }
		else {
            System.out.println("Jwt not start with Bearer");
        }

        if(tokenPayLoad != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Account> accountOptional = accountRepository.findById(tokenPayLoad.getAccountId());
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                if(jwtTokenUtil.isValid(token, AccountMapper.toTokenPayLoad(account))) {
                    //Tạo user detail và lưu vào context holder
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    UserDetails userDetails = new User(account.getUsername(), account.getPassword(), authorities);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
