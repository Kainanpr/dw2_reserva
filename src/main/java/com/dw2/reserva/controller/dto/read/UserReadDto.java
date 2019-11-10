package com.dw2.reserva.controller.dto.read;

import com.dw2.reserva.model.TypeEnum;
import com.dw2.reserva.util.ObjectBuilder;

import java.util.Objects;

public final class UserReadDto {
    private final Integer id;
    private final String name;
    private final String email;
    private final String cpf;
    private final TypeEnum type;

    private UserReadDto(Integer id, String name, String email, String cpf, TypeEnum type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public TypeEnum getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReadDto that = (UserReadDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(cpf, that.cpf) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, cpf, type);
    }

    @Override
    public String toString() {
        return "UserReadDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", type=" + type +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String name;
        private String email;
        private String cpf;
        private TypeEnum type;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder setType(TypeEnum type) {
            this.type = type;
            return this;
        }

        @Override
        public UserReadDto build() {
            return new UserReadDto(id, name, email, cpf, type);
        }
    }
}
