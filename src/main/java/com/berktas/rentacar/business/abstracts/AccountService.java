package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.controller.account.LoginRequest;
import com.berktas.rentacar.controller.account.LoginResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {
    LoginResponse login(LoginRequest loginRequest);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
