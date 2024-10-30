package edu.unimagdalena.reservadevuelo.exeptions;

public class ClientNotFoundException extends ResourceNotFoundException{
    public ClientNotFoundException() {
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
