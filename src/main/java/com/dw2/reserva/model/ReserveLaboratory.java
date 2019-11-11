package com.dw2.reserva.model;

import com.dw2.reserva.util.ObjectBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.util.Objects;

public final class ReserveLaboratory {
    private final Integer id;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final User user;
    private final Laboratory laboratory;

    private ReserveLaboratory(Integer id, LocalDateTime startDate, LocalDateTime endDate, User user, Laboratory laboratory) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.laboratory = laboratory;
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

    public Laboratory getLaboratory() {
        return laboratory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReserveLaboratory that = (ReserveLaboratory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(user, that.user) &&
                Objects.equals(laboratory, that.laboratory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, user, laboratory);
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting reserveLaboratory to json", e);
        }
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private User user;
        private Laboratory laboratory;


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

        public Builder setLaboratory(Laboratory laboratory) {
            this.laboratory = laboratory;
            return this;
        }

        @Override
        public ReserveLaboratory build() {
            return new ReserveLaboratory(id, startDate, endDate, user, laboratory);
        }
    }
}
