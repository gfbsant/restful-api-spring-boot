package br.com.restful.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.restful.model.User;
import br.com.restful.repository.UserRepository;
import br.com.restful.security.jwt.JwtTokenProvider;
import br.com.restful.vo.v1.security.AccountCredentialsValueObject;
import br.com.restful.vo.v1.security.TokenValueObject;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<?> signin(AccountCredentialsValueObject data) {
        try {
            String userName = data.getUserName();
            String password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password));
            User user = repository.findByUserName(userName);
            TokenValueObject tokenResponse = new TokenValueObject();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(userName, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + userName + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    public ResponseEntity<?> refreshToken(String username, String refreshToken) {
        User user = repository.findByUserName(username);
        TokenValueObject tokenResponse = new TokenValueObject();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
