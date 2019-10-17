package com.dw2.reserva.rest.dto.write;

import com.dw2.reserva.model.TypeEnum;
import com.dw2.reserva.util.ObjectBuilder;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public final class UserWriteDto {
    @NotBlank
    private final String name;
    @NotBlank
    private final String email;
    @NotBlank
    private final String cpf;
    @NotNull
    private final TypeEnum type;

    @JsonCreator
    private UserWriteDto(String name, String email, String cpf, TypeEnum type) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.type = type;
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
        UserWriteDto that = (UserWriteDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(cpf, that.cpf) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, cpf, type);
    }

    @Override
    public String toString() {
        return "UserWriteDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", type=" + type +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private String name;
        private String email;
        private String cpf;
        private TypeEnum type;

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
        public UserWriteDto build() {
            return new UserWriteDto(name, email, cpf, type);
        }
    }
}
