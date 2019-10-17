package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.User;

import java.util.List;

public interface UserRepository {
    User getById(Integer id);

    List<User> getAll();

    int save(User user);

    int update(User user);

    int delete(Integer id);
}
