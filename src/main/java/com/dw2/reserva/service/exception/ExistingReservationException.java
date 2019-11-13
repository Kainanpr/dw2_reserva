package com.dw2.reserva.service.exception;

public class ExistingReservationException extends RuntimeException {

    public ExistingReservationException(String msg) {
        super(msg);
    }
}
