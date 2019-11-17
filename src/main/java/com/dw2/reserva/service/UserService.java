package com.dw2.reserva.service;

import com.dw2.reserva.model.User;
import com.dw2.reserva.persistence.repository.UserRepository;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(Integer id) {
        final User user = userRepository.getById(id);

        if (user == null) {
            throw new ObjectNotFoundException("User not found! ID: " + id);
        }

        LOGGER.info("Retrieved user by ID: {}", user);
        return user;
    }

    public List<User> getAll() {
        final List<User> userList = userRepository.getAll();
        LOGGER.info("Retrieved all users: {}", userList);
        return userList;
    }


    public List<User> getAllTeachers() {
        final List<User> userList = userRepository.getAllTeachers();
        LOGGER.info("Retrieved all teachers: {}", userList);
        return userList;
    }

    @Transactional
    public User save(User user) {
        final int savedId = userRepository.save(user);

        final User savedUser = userRepository.getById(savedId);
        LOGGER.info("Saved User: {}", savedUser);
        return savedUser;
    }

    @Transactional
    public User update(User user) {
        final int userId = user.getId();
        final int affectedRows = userRepository.update(user);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find user with id (not updated): " + userId);
        }

        final User updatedUser = userRepository.getById(userId);
        LOGGER.info("Updated User: {}", updatedUser);
        return updatedUser;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = userRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find user with id (not deleted): " + id);
        }

        LOGGER.info("Deleted user (deleted rows: {})", affectedRows);
    }
}
