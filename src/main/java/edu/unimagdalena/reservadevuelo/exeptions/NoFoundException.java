package edu.unimagdalena.reservadevuelo.exeptions;

public class NoFoundException extends RuntimeException {

    NoFoundException() {

    }

    NoFoundException(String message) {
        super(message);
    }

    NoFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
