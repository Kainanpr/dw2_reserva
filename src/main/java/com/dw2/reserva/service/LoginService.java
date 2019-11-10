package com.dw2.reserva.service;

import com.dw2.reserva.model.Login;
import com.dw2.reserva.persistence.repository.LoginRepository;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Login isAuthenticated(Login login) {
        final Login loginFound = loginRepository.isAuthenticated(login);

        if (loginFound == null) {
            throw new ObjectNotFoundException("Login not found! Login: " + loginFound);
        }

        LOGGER.info("Retrieved login: {}", loginFound);
        return loginFound;
    }
}
