package fon.njt.cvbuilderapi.service;

import fon.njt.cvbuilderapi.dto.AuthenticationResponse;
import fon.njt.cvbuilderapi.dto.LoginRequest;
import fon.njt.cvbuilderapi.dto.RefreshTokenRequest;
import fon.njt.cvbuilderapi.dto.RegisterRequest;
import fon.njt.cvbuilderapi.exceptions.CVBuilderException;
import fon.njt.cvbuilderapi.exceptions.UserNotFoundException;
import fon.njt.cvbuilderapi.model.NotificationEmail;
import fon.njt.cvbuilderapi.model.User;
import fon.njt.cvbuilderapi.model.UserType;
import fon.njt.cvbuilderapi.model.VerificationToken;
import fon.njt.cvbuilderapi.repository.UserRepository;
import fon.njt.cvbuilderapi.repository.VerificationTokenRepository;
import fon.njt.cvbuilderapi.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;


    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setAddress(registerRequest.getAddress());
        user.setName(registerRequest.getName());
        user.setSurname(registerRequest.getSurname());
        user.setUserType(UserType.REGULAR_USER);
        user.setPremium(false);
        user = userRepository.save(user);

        String token = generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Please activate your account", user.getEmail(), "http://localhost:8080/auth/accountVerification/" + token));

    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new CVBuilderException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());

    }

    @Transactional
    protected void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new CVBuilderException("User not found " +
                username));
        user.setEnabled(true);
        userRepository.save(user);
    }


    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    @Transactional
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with name - " + principal.getUsername()));
    }



    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public String updateUser(User user) {
        Optional<User> u = userRepository.findByUsername(user.getUsername());
        User u1 = new User();
        u1.setUserId(u.get().getUserId());
        u1.setPremium(true);
        u1.setEnabled(u.get().isEnabled());
        u1.setEmail(u.get().getEmail());
        u1.setAddress(u.get().getAddress());
        u1.setUsername(u.get().getUsername());
        u1.setUserType(u.get().getUserType());
        u1.setPassword(u.get().getPassword());
        u1.setName(u.get().getName());
        u1.setSurname(u.get().getSurname());
        //userRepository.setUserInfoById(true, u.get().getUserId());
        userRepository.save(u1);
        return "uspesno";
    }

    public boolean isUserPremium(User user) {
        Optional<User> u = userRepository.findByUsername(user.getUsername());
        try{
            if(u.get().isPremium())
                return true;
            return false;
        }catch (Exception ex){
            return false;
        }
    }
}
