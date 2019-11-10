package com.dw2.reserva.model;

import com.dw2.reserva.util.ObjectBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public final class Equipment {
    private final Integer id;
    private final String name;
    private final Laboratory laboratory;

    private Equipment(Integer id, String name, Laboratory laboratory) {
        this.id = id;
        this.name = name;
        this.laboratory = laboratory;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id) &&
                Objects.equals(name, equipment.name) &&
                Objects.equals(laboratory, equipment.laboratory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, laboratory);
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting equipment to json", e);
        }
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String name;
        private Laboratory laboratory;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLaboratory(Laboratory laboratory) {
            this.laboratory = laboratory;
            return this;
        }

        @Override
        public Equipment build() {
            return new Equipment(id, name, laboratory);
        }
    }
}
