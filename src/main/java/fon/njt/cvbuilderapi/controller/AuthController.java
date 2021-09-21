package fon.njt.cvbuilderapi.controller;


import fon.njt.cvbuilderapi.dto.AuthenticationResponse;
import fon.njt.cvbuilderapi.dto.LoginRequest;
import fon.njt.cvbuilderapi.dto.RefreshTokenRequest;
import fon.njt.cvbuilderapi.dto.RegisterRequest;
import fon.njt.cvbuilderapi.model.Template;
import fon.njt.cvbuilderapi.model.User;
import fon.njt.cvbuilderapi.service.AuthService;
import fon.njt.cvbuilderapi.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("auth/")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return ResponseEntity.status(HttpStatus.OK).body("User Registration Successful");

    }

    @PostMapping("login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully. Please go to Login page and try our app", OK); //HttpStatus.OK
    }

    @PostMapping("isPremium")
    public ResponseEntity<Boolean> checkIsUserPremium(@RequestBody User u) {
        boolean isPremium = authService.isUserPremium(u);
        return new ResponseEntity(isPremium, HttpStatus.OK);
    }

    @PostMapping("refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("premium")
    public ResponseEntity<String> updateTemplate(@RequestBody User u){
        authService.updateUser(u);
        return new ResponseEntity(HttpStatus.OK);
    }


}
