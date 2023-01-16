package com.berktas.rentacar.controller.account;


import com.berktas.rentacar.business.abstracts.AccountService;
import com.berktas.rentacar.core.util.SpringContext;
import com.berktas.rentacar.core.util.annotation.IsAuthenticated;
import com.berktas.rentacar.core.util.annotation.OnlyAdmin;
import com.berktas.rentacar.core.util.annotation.PermitAllCustom;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("account")
@IsAuthenticated
@RequiredArgsConstructor
@Tag(name = "Account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public MeResponse me() {
        return SpringContext.getMe();
    }


    @PostMapping("/login")
    @PermitAllCustom
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        return accountService.login(loginRequest);
    }


    @GetMapping("/logout")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        accountService.logout(request, response);
    }
}
