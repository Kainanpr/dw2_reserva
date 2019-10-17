package com.dw2.reserva.rest;

import com.dw2.reserva.model.User;
import com.dw2.reserva.rest.dto.read.UserReadDto;
import com.dw2.reserva.rest.dto.write.UserWriteDto;
import com.dw2.reserva.service.UserService;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/users")
public class UserRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserReadDto> getById(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to return user: {}", id);

        try {
            final User user = userService.getById(id);
            final UserReadDto dto = toUserReadDto(user);
            return ResponseEntity.ok(dto);
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private UserReadDto toUserReadDto(User user) {
        return new UserReadDto.Builder()
                .setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setCpf(user.getCpf())
                .setType(user.getType())
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserReadDto>> getAll() {
        final List<User> userList = userService.getAll();
        final List<UserReadDto> responseBody = new ArrayList<>(userList.size());

        for (User user : userList) {
            responseBody.add(toUserReadDto(user));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserReadDto> create(@RequestBody @Valid UserWriteDto userWrite) {
        LOGGER.info("User received to save: {}", userWrite);

        final User user = toUserModel(null, userWrite);
        final User savedUser = userService.save(user);

        final UserReadDto responseBody = toUserReadDto(savedUser);
        return ResponseEntity
                .created(URI.create("/v1/users/" + savedUser.getId()))
                .body(responseBody);
    }

    private User toUserModel(Integer id, UserWriteDto userWrite) {
        return new User.Builder()
                .setId(id)
                .setName(userWrite.getName())
                .setEmail(userWrite.getEmail())
                .setCpf(userWrite.getCpf())
                .setType(userWrite.getType())
                .build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserReadDto> update(@PathVariable("id") Integer id, @RequestBody @Valid UserWriteDto userWrite) {
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("User received to update: {}", userWrite);

        final User user = toUserModel(id, userWrite);

        try {
            final User updatedUser = userService.update(user);

            final UserReadDto responseBody = toUserReadDto(updatedUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseBody);
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to delete: {}", id);

        try {
            userService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
