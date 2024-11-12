package edu.unimagdalena.reservadevuelo.controllers;

import edu.unimagdalena.reservadevuelo.dto.security.JwtResponse;
import edu.unimagdalena.reservadevuelo.dto.security.LoginRequest;
import edu.unimagdalena.reservadevuelo.dto.security.SignupRequest;
import edu.unimagdalena.reservadevuelo.dto.security.UsernameRequest;
import edu.unimagdalena.reservadevuelo.entities.ERole;
import edu.unimagdalena.reservadevuelo.entities.Role;
import edu.unimagdalena.reservadevuelo.entities.User;
import edu.unimagdalena.reservadevuelo.repositories.RoleRepository;
import edu.unimagdalena.reservadevuelo.repositories.UserRepository;
import edu.unimagdalena.reservadevuelo.security.jwt.JwtUtil;
import edu.unimagdalena.reservadevuelo.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(),
                        loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken= jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwtToken, "Bearer", userDetails.getUsername(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest sRequest){
        Set<String> roleNames = sRequest.roles();
        Set<Role> roles = roleNames.stream()
                .map(roleName -> roleRepository.findByName(ERole.valueOf(roleName))
                        .orElseThrow(() -> new RuntimeException("Error: Role not found")))
                .collect(Collectors.toSet());

        User user = new User(null,
                sRequest.username(),
                passwordEncoder.encode(sRequest.password()),
                sRequest.email(),
                roles);
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/submit/username")
    public ResponseEntity<?> submitEmail(@RequestBody UsernameRequest req){
        Optional<User> user = userRepository.findByUsername(req.username());
        Map<String,String> response = new HashMap<>();
        if(user.isEmpty()) response.put("NEXT_STEP", "USER_REGISTER");
        else response.put("NEXT_STEP", "USER_LOGIN");

        return ResponseEntity.ok(response);
    }
}