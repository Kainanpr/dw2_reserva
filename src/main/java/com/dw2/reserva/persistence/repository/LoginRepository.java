package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.Login;

public interface LoginRepository {
    Login isAuthenticated(Login login);
}
