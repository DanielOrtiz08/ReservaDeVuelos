package edu.unimagdalena.reservadevuelo.dto.security;

import java.util.Set;

public record SignupRequest (String username, String password, String email, Set<String> roles){
}
