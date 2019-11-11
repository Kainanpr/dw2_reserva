package com.dw2.reserva.model;

import com.dw2.reserva.util.ObjectBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Objects;

public final class ReserveEquipment {
    private final Integer id;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final User user;
    private final Equipment equipment;

    private ReserveEquipment(Integer id, LocalDateTime startDate, LocalDateTime endDate, User user, Equipment equipment) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.equipment = equipment;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public User getUser() {
        return user;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReserveEquipment that = (ReserveEquipment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(user, that.user) &&
                Objects.equals(equipment, that.equipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, user, equipment);
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting reserveEquipment to json", e);
        }
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private User user;
        private Equipment equipment;


        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setEquipment(Equipment equipment) {
            this.equipment = equipment;
            return this;
        }

        @Override
        public ReserveEquipment build() {
            return new ReserveEquipment(id, startDate, endDate, user, equipment);
        }
    }
}
