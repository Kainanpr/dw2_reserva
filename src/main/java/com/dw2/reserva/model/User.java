package com.dw2.reserva.model;

import com.dw2.reserva.util.ObjectBuilder;

import java.util.Objects;

public final class User {
    private final Integer id;
    private final String name;
    private final String email;
    private final String cpf;
    private final TypeEnum type;

    private User(Integer id, String name, String email, String cpf, TypeEnum type) {
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
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(cpf, user.cpf) &&
                type == user.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, cpf, type);
    }

    @Override
    public String toString() {
        return "User{" +
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
        public User build() {
            return new User(id, name, email, cpf, type);
        }
    }
}
